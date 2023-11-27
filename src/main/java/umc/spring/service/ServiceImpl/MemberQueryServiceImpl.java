package umc.spring.service.ServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.repository.MemberRepository;
import umc.spring.service.MemberQueryService;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;
    @Override
    public boolean isExistMember(Long id) {
        return memberRepository.existsById(id);
    }
}
