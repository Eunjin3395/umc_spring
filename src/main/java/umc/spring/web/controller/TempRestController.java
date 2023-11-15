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
public class TempRestController {

    private final TempQueryService tempQueryService; // @RequiredArgsConstructor를 이용한 의존성 주입

    @GetMapping("/test")
    public ApiResponse<TempResponse.TempTestDTO> testAPI() { // 이 api는 ApiResponse 형태의 데이터를 반환하는데, ApiResponse의 result 필드에는 TempTestDTO가 들어간 TempTestDTO는 testString이라는 데이터를 가짐.
        return ApiResponse.onSuccess(TempConverter.toTempTestDTO()); // ApiResponse의 성공 응답을 줄건데, result 필드에는 TempTestDTO를 담아서 줄 것. 이때 DTO는 TempConverter를 이용해 생성하고, 컨버터에서 testString key에 대한 value를 담아 DTO를 만들어줌
    }

    @GetMapping("/exception")
    public ApiResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag) {
        tempQueryService.CheckFlag(flag); // flag가 1이면 여기서 exception 발생함f
        return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
    }

}
