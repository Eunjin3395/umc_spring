package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.validation.annotation.ExistAddress;
import umc.spring.validation.annotation.ExistCategories;

import javax.validation.constraints.NotBlank;

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
}
