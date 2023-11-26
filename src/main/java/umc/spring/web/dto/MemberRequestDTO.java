package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class JoinDto {
        @NotBlank
        String name;
        @NotNull
        Integer gender;
        @NotNull
        Integer birthYear;
        @NotNull
        Integer birthMonth;
        @NotNull
        Integer birthDay;
//        @Size(min=5,max=12)
        Long address;
        @Size(min=5,max=12)
        String specAddress;
        @NotNull
        String phone;
        @ExistCategories
        List<Long> favorCategory;
    }
}
