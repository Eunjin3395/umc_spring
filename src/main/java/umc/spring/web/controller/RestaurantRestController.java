package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.RestaurantConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.restaurant.Restaurant;
import umc.spring.domain.review.Review;
import umc.spring.service.RestaurantCommandService;
import umc.spring.service.RestaurantQueryService;
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
    private final RestaurantQueryService restaurantQueryService;
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
    ) {
        Mission mission = restaurantCommandService.addMission(request, restaurantId);
        return ApiResponse.onSuccess(RestaurantConverter.toAddMissionResultDTO(mission));
    }

    @GetMapping("/{restaurantId}/reviews")
    @Operation(summary = "특정 음식점의 리뷰 목록 조회 API", description = "특정 음식점의 리뷰들의 목록을 조회하는 API, 페이징을 포함함. query String으로 page 번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "음식점의 아이디, path variable 입니다."),
            @Parameter(name = "page", description = "페이지 번호, 0번이 페이지 1 입니다.")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistRestaurant @PathVariable(name = "restaurantId") Long restaurantId, @RequestParam(name = "page") Integer page) {
        Page<Review> reviewList = restaurantQueryService.getReviewList(restaurantId, page);
        return ApiResponse.onSuccess(ReviewConverter.toReviewPreViewListDTO(reviewList));
    }

    @GetMapping("/{restaurantId}/missions")
    @Operation(summary = "특정 음식점의 미션 목록 조회 API", description = "특정 음식점의 미션들의 목록을 조회하는 API, 페이징을 포함함. query String으로 page 번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            })
    @Parameters({
            @Parameter(name = "restaurantId", description = "음식점의 아이디, path variable 입니다."),
            @Parameter(name = "page", description = "페이지 번호, query string 입니다.")
    })
    public ApiResponse<RestaurantResponseDTO.MissionPreViewListDTO> getMissionList(
            @PathVariable(name = "restaurantId") @ExistRestaurant Long restaurantId,
            @RequestParam(name = "page") Integer page) {
        Page<Mission> missionList = restaurantQueryService.getMissionList(restaurantId, page);
        return ApiResponse.onSuccess(RestaurantConverter.toMissionPreViewListDTO(missionList));
    }

}
