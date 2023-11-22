package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.TempConverter;
import umc.spring.service.TempService.TempQueryService;
import umc.spring.web.dto.TempResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
public class TempController {

    private final TempQueryService tempQueryService;

    @GetMapping("/test")
    public ApiResponse<TempResponse.TempTestDto> testAPI() {
        return ApiResponse.onSuccess(TempConverter.toTempTestDto());
    }

    @GetMapping("/exception")
    public ApiResponse<TempResponse.TempExceptionDto> exceptionAPI(@RequestParam Integer flag){
        tempQueryService.checkFlag(flag);
        return ApiResponse.onSuccess(TempConverter.toTempExceptionDto(flag));
    }

}
