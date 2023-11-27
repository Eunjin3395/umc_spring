package umc.spring.service.ServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.RestaurantHandler;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.restaurant.Restaurant;
import umc.spring.domain.review.Review;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.RestaurantRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.service.ReviewCommandService;
import umc.spring.web.dto.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Review addReview(ReviewRequestDTO.addDto request, Long restaurantId) {

        // contents, score만 컨버터에서 설정
        Review newReview = ReviewConverter.toReview(request);

        // Member는 id 1이라고 가정하고 리뷰 데이터 설정
        Member member = memberRepository.findById(1L).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantHandler(ErrorStatus.RESTAURANT_NOT_FOUND));

        // 연관관계 편의 메소드로 양방향 관계 설정
        newReview.setMember(member);
        newReview.setRestaurant(restaurant);

        return reviewRepository.save(newReview);
    }
}
