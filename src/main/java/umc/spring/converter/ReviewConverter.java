package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.restaurant.Restaurant;
import umc.spring.domain.review.Review;
import umc.spring.web.dto.RestaurantResponseDTO;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

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

    public static ReviewResponseDTO.ReviewPreViewDTO toReviewPreViewDTO(Review review) {
        return ReviewResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getNickname())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getContents())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreViewListDTO toReviewPreViewListDTO(Page<Review> reviewList) {
        List<ReviewResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList=reviewList.stream()
                .map(ReviewConverter::toReviewPreViewDTO).collect(Collectors.toList());

        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static ReviewResponseDTO.MyReviewPreViewDTO toMyReviewPreViewDTO(Review review) {
        return ReviewResponseDTO.MyReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getNickname())
                .restaurantName(review.getRestaurant().getName())
                .score(review.getScore())
                .body(review.getContents())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static ReviewResponseDTO.MyReviewPreViewListDTO toMyReviewPreViewListDTO(Page<Review> reviewList) {
        List<ReviewResponseDTO.MyReviewPreViewDTO> myReviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::toMyReviewPreViewDTO).collect(Collectors.toList());

        return ReviewResponseDTO.MyReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(myReviewPreViewDTOList.size())
                .myReviewList(myReviewPreViewDTOList)
                .build();
    }
}
