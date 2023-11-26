package umc.spring.converter;

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

    public static Restaurant toRestaurant(RestaurantRequestDTO.AddDto request) {
        return Restaurant.builder()
                .name(request.getName())
                .address(null)
                .foodCategory(null) // Long타입 id를 가지고 address와 foodCategory 엔티티 찾아서 넣어야 함
                .specAddress(request.getSpecAddress())
                .build();
    }
}
