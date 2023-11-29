package umc.spring.service;

import umc.spring.domain.mapping.MemberMission;

import java.util.Optional;

public interface MissionQueryService {
    public boolean isExistMission(Long id);
    public Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId);

}
