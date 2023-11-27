package umc.spring.service;

import umc.spring.domain.Mission;
import umc.spring.domain.restaurant.Restaurant;
import umc.spring.web.dto.RestaurantRequestDTO;

public interface RestaurantCommandService {
    public Restaurant addRestaurant(RestaurantRequestDTO.AddDto request);

    public Mission addMission(RestaurantRequestDTO.AddMissionDto request, Long restaurantId);
}
