package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.repository.MissionRepository;
import umc.spring.service.MissionQueryService;
import umc.spring.validation.annotation.ExistMission;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class MissionExistValidator implements ConstraintValidator<ExistMission, Long> {
    private final MissionQueryService missionQueryService;
    @Override
    public void initialize(ExistMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        boolean isValid = missionQueryService.isExistMission(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_NOT_FOUND.toString()).addConstraintViolation();
        }
        return isValid;
    }
}
