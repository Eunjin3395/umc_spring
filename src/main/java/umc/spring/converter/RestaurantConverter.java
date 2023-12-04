package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Mission;
import umc.spring.domain.restaurant.Restaurant;
import umc.spring.web.dto.RestaurantRequestDTO;
import umc.spring.web.dto.RestaurantResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public static RestaurantResponseDTO.MissionPreViewDTO toMissionPreViewDTO(Mission mission) {
        return RestaurantResponseDTO.MissionPreViewDTO.builder()
                .description(mission.getDescription())
                .point(mission.getPoint())
                .amount(mission.getAmount())
                .dueDate(mission.getDueDate())
                .createdAt(mission.getCreatedAt().toLocalDate())
                .build();
    }

    public static RestaurantResponseDTO.MissionPreViewListDTO toMissionPreViewListDTO(Page<Mission> missionList) {
        List<RestaurantResponseDTO.MissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(RestaurantConverter::toMissionPreViewDTO).collect(Collectors.toList());

        return RestaurantResponseDTO.MissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }
}
