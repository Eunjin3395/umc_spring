package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberFavorConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberFavor;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        // request DTO를 Member 엔티티로 변환하기
        Member newMember = MemberConverter.toMember(request);

        // request의 favorCategory List를 돌면서 카테고리 존재하는지 검증
        List<FoodCategory> foodCategoryList = request.getFavorCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        // 위에서 얻은 foodCategoryList로 MemberFavor 엔티티 생성
        List<MemberFavor> memberFavorList = MemberFavorConverter.toMemberFavorList(foodCategoryList);

        // 양방향 연관관계 설정
        memberFavorList.forEach(memberFavor -> {memberFavor.setMember(newMember);});

        return memberRepository.save(newMember);
    }
}
