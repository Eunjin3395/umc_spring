package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.restaurant.Restaurant;
import umc.spring.domain.review.Review;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

public class ReviewConverter {
    public static ReviewResponseDTO.AddResultDTO toAddResultDTO(Review review) {
        return ReviewResponseDTO.AddResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.addDto request) {
        return Review.builder()
                .contents(request.getContents())
                .score(request.getScore())
                .build();
    }
}
