package umc.spring.web.dto;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class ReviewRequestDTO {

    @Getter
    public static class addDto{
        @Size(max=2000,message = "최대 2000자까지 작성 가능합니다.")
        String contents;
        float score;
    }
}
