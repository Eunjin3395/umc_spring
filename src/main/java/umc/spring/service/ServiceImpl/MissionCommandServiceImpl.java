package umc.spring.service.ServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.MemberConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.service.MissionCommandService;
import umc.spring.web.dto.MemberRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;


    @Override
    public MemberMission startMission(MemberRequestDTO.startMissionDto request) {

        MemberMission memberMission = MemberConverter.toMemberMission(MissionStatus.ONGOING);
        Mission mission = missionRepository.findById(request.getMissionId()).get();
        Member member = memberRepository.findById(request.getMemberId()).get();

        memberMission.setMission(mission);
        memberMission.setMember(member);

        return memberMissionRepository.save(memberMission);
    }
}
