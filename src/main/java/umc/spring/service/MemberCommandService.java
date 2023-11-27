package umc.spring.service;

import umc.spring.domain.Member;
import umc.spring.web.dto.MemberRequestDTO;

public interface MemberCommandService {
    public Member joinMember(MemberRequestDTO.JoinDto request);
}
