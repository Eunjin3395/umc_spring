package umc.spring.service;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.review.Review;

public interface MemberQueryService {

    public boolean isExistMember(Long id);

    Page<Review> getReviewList(Long memberId, Integer page);
}
