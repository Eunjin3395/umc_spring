package umc.spring.converter;

import umc.spring.web.dto.TempResponse;

public class TempConverter {
    public static TempResponse.TempTestDto toTempTestDto() {
        return TempResponse.TempTestDto.builder()
                .testString("This is test!")
                .build();
    }

    public static TempResponse.TempExceptionDto toTempExceptionDto(Integer flag){
        return TempResponse.TempExceptionDto.builder()
                .flag(flag)
                .build();
    }
}
