package umc.spring.service.ServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Member;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.service.MissionQueryService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Override
    public boolean isExistMission(Long id) {
        return missionRepository.existsById(id);
    }

    @Override
    public Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId) {
        return memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId);
    }

    @Override
    public Page<MemberMission> getMissionList(Long memberId, MissionStatus status, Integer page) {
        Member member = memberRepository.findById(memberId).get();

        Page<MemberMission> memberMissionPage = null;

        if (status == MissionStatus.ONGOING) {
            memberMissionPage = memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.ONGOING, PageRequest.of(page,10));
        } else if (status == MissionStatus.COMPLETED) {
            memberMissionPage = memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.COMPLETED, PageRequest.of(page, 10));
        }

        return memberMissionPage;
    }
}
