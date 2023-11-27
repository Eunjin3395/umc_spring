package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.validation.annotation.ExistAddress;
import umc.spring.validation.annotation.ExistCategories;

import javax.validation.constraints.*;

public class RestaurantRequestDTO {

    @Getter
    public static class AddDto{
        @NotBlank
        String name;
        @ExistAddress
        Long addressId;
        @ExistCategories
        Long foodId;
        @NotBlank
        String specAddress;
    }

    @Getter
    public static class AddMissionDto{

        @Size(max = 255,message = "{최대 255자까지 작성 가능합니다.}")
        @NotBlank
        String description;

        @NotNull
        Integer point;

        @NotNull
        Integer amount;

        @Min(value = 2023)
        Integer dueYear;

        @Min(value = 1)
        @Max(value = 12)
        Integer dueMonth;

        @Min(value = 1)
        @Max(value = 31)
        Integer dueDate;
    }
}
