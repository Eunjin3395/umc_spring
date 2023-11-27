package umc.spring.converter;

import umc.spring.domain.FoodCategory;
import umc.spring.domain.Mission;
import umc.spring.domain.restaurant.Restaurant;
import umc.spring.web.dto.RestaurantRequestDTO;
import umc.spring.web.dto.RestaurantResponseDTO;

import java.time.LocalDate;

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

    public static RestaurantResponseDTO.AddMissionResultDto toAddMissionResultDTO(Mission mission) {
        return RestaurantResponseDTO.AddMissionResultDto.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    public static Mission toMission(RestaurantRequestDTO.AddMissionDto request) {

        LocalDate dueDate = LocalDate.of(request.getDueYear(), request.getDueMonth(), request.getDueDate());

        return Mission.builder()
                .description(request.getDescription())
                .amount(request.getAmount())
                .point(request.getPoint())
                .dueDate(dueDate)
                .build();
    }
}
