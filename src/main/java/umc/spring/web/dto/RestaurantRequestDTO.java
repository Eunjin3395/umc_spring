package umc.spring.web.dto;

import lombok.Getter;

public class RestaurantRequestDTO {

    @Getter
    public static class AddDto{
        String name;
        Long addressId;
        Long foodId;
        String specAddress;
    }
}
