package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.RestaurantConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.restaurant.Restaurant;
import umc.spring.domain.review.Review;
import umc.spring.service.RestaurantCommandService;
import umc.spring.service.ReviewCommandService;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistRestaurant;
import umc.spring.web.dto.RestaurantRequestDTO;
import umc.spring.web.dto.RestaurantResponseDTO;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantRestController {

    private final RestaurantCommandService restaurantCommandService;
    private final ReviewCommandService reviewCommandService;

    @PostMapping("/")
    public ApiResponse<RestaurantResponseDTO.AddResultDTO> addRestaurant(@RequestBody @Valid RestaurantRequestDTO.AddDto request) {
        Restaurant restaurant = restaurantCommandService.addRestaurant(request);
        return ApiResponse.onSuccess(RestaurantConverter.toAddResultDTO(restaurant));
    }

    @PostMapping("/{restaurantId}/reviews")
    public ApiResponse<ReviewResponseDTO.AddResultDTO> addReview(
            @PathVariable @ExistRestaurant Long restaurantId,
            @RequestBody @Valid ReviewRequestDTO.addDto request) {
        Review review = reviewCommandService.addReview(request, restaurantId);
        return ApiResponse.onSuccess(ReviewConverter.toAddResultDTO(review));
    }

    @PostMapping("/{restaurantId}/missions")
    public ApiResponse<RestaurantResponseDTO.AddMissionResultDto> addMission(
            @PathVariable @ExistRestaurant Long restaurantId,
            @RequestBody @Valid RestaurantRequestDTO.AddMissionDto request
    ){
        Mission mission = restaurantCommandService.addMission(request, restaurantId);
        return ApiResponse.onSuccess(RestaurantConverter.toAddMissionResultDTO(mission));
    }
}
