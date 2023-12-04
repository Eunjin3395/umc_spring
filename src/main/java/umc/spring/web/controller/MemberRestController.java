package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.review.Review;
import umc.spring.service.MemberCommandService;
import umc.spring.service.MemberQueryService;
import umc.spring.service.MissionCommandService;
import umc.spring.service.MissionQueryService;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.validation.annotation.MissionAvailable;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request) {
        Member member = memberCommandService.joinMember(request);
        log.info("member = ", member);

        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/missions")
    public ApiResponse<MemberResponseDTO.StartMissionResultDTO> startMission(@RequestBody @MissionAvailable @Valid MemberRequestDTO.startMissionDto request) {
        MemberMission memberMission = missionCommandService.startMission(request);
        return ApiResponse.onSuccess(MemberConverter.toStartMissionResultDTO(memberMission));
    }

    @GetMapping("/reviews")
    @Operation(summary = "특정 회원의 리뷰 목록 조회 API", description = "특정 회원이 작성한 리뷰들의 목록을 조회하는 API, 페이징을 포함함. query String으로 member id와 page 번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디 번호, query string 입니다."),
            @Parameter(name = "page", description = "페이지 번호, 1 이상의 숫자를 입려해주세요.")
    })
    public ApiResponse<ReviewResponseDTO.MyReviewPreViewListDTO> getMyReviewList(
            @RequestParam(name = "memberId") @ExistMember Long memberId,
            @RequestParam(name = "page") Integer page
    ) {
        Page<Review> reviewList = memberQueryService.getReviewList(memberId, page);
        return ApiResponse.onSuccess(ReviewConverter.toMyReviewPreViewListDTO(reviewList));
    }

    @GetMapping("/missions")
    @Operation(summary = "특정 회원의 상태별 미션 목록 조회 API", description = "특정 회원이 진행중인/진행완료한 미션 목록을 조회하는 API, 페이징을 포함함. query String으로 member id, status, page 번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디 번호, query string 입니다."),
            @Parameter(name = "status", description = "미션 상태, query string 입니다. ongoing 또는 completed 만 허용됩니다."),
            @Parameter(name = "page", description = "페이지 번호, query string 입니다.")
    })
    public ApiResponse<MemberResponseDTO.memberMissionPreViewListDTO> getMissionList(
            @RequestParam(name = "memberId") @ExistMember Long memberId,
            @RequestParam(name="status") String status,
            @RequestParam(name="page") Integer page
    ){
        MissionStatus missionStatus = MissionStatus.of(status);
        Page<MemberMission> memberMissionList = missionQueryService.getMissionList(memberId, missionStatus, page);
        return ApiResponse.onSuccess(MemberConverter.toMemberMissionPreViewListDTO(missionStatus,memberMissionList));
    }
}
