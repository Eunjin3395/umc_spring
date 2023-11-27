package umc.spring.converter;

import umc.spring.domain.FoodCategory;
import umc.spring.domain.restaurant.Restaurant;
import umc.spring.web.dto.RestaurantRequestDTO;
import umc.spring.web.dto.RestaurantResponseDTO;

public class RestaurantConverter {

    public static RestaurantResponseDTO.AddResultDTO toAddResultDTO(Restaurant restaurant) {
        return RestaurantResponseDTO.AddResultDTO.builder()
                .restaurantId(restaurant.getId())
                .createdAt(restaurant.getCreatedAt())
                .build();
    }

    public static Restaurant toRestaurant(RestaurantRequestDTO.AddDto request, FoodCategory foodCategory) {
        return Restaurant.builder()
                .name(request.getName())
                .specAddress(request.getSpecAddress())
                .foodCategory(foodCategory)
                .build();
    }
}
