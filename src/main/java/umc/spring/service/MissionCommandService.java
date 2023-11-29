package umc.spring.service;

import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberRequestDTO;

public interface MissionCommandService {
    public MemberMission startMission(MemberRequestDTO.startMissionDto request);
}
