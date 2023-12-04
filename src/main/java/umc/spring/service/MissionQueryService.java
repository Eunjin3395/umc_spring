package umc.spring.service;

import org.springframework.data.domain.Page;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

import java.util.Optional;

public interface MissionQueryService {
    public boolean isExistMission(Long id);
    public Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId);

    Page<MemberMission> getMissionList(Long memberId, MissionStatus status, Integer page);

}
