package umc.spring.service;

import umc.spring.domain.review.Review;
import umc.spring.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {
    public Review addReview(ReviewRequestDTO.addDto request, Long restaurantId);
}
