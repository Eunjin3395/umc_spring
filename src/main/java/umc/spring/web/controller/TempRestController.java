package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.TempConverter;
import umc.spring.web.dto.TempResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
public class TempRestController {
    @GetMapping("/test")
    public ApiResponse<TempResponse.TempTestDTO> testAPI(){ // 이 api는 ApiResponse 형태의 데이터를 반환하는데, ApiResponse의 result 필드에는 TempTestDTO가 들어간 TempTestDTO는 testString이라는 데이터를 가짐.
        return ApiResponse.onSuccess(TempConverter.toTempTestDTO()); // ApiResponse의 성공 응답을 줄건데, result 필드에는 TempTestDTO를 담아서 줄 것. 이때 DTO는 TempConverter를 이용해 생성하고, 컨버터에서 testString key에 대한 value를 담아 DTO를 만들어줌
    }
}
