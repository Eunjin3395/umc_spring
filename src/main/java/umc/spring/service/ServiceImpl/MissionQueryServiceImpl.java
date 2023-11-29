package umc.spring.service.ServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.service.MissionQueryService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    @Override
    public boolean isExistMission(Long id) {
        return missionRepository.existsById(id);
    }

    @Override
    public Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId) {
        return memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId);
    }
}
