package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MissionQueryService;
import umc.spring.validation.annotation.MissionAvailable;
import umc.spring.web.dto.MemberRequestDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MissionAvailableValidator implements ConstraintValidator<MissionAvailable, Object> {
    private final MissionQueryService missionQueryService;

    @Override
    public void initialize(MissionAvailable constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof MemberRequestDTO.startMissionDto) {
            MemberRequestDTO.startMissionDto request = (MemberRequestDTO.startMissionDto) value;

            // 멤버id와 미션id가 동일한 memberMission 엔티티 조회
            Optional<MemberMission> memberMission=
            missionQueryService.findByMemberIdAndMissionId(request.getMemberId(), request.getMissionId());

            if (!memberMission.isEmpty()) {
                if (memberMission.get().getStatus() == MissionStatus.ONGOING) { // 해당 memberMission의 status가 ONGOING이면 미션 시작 불가능
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_STARTED.toString()).addConstraintViolation();
                    return false;
                }
            }
        }
        return true;
    }
}
