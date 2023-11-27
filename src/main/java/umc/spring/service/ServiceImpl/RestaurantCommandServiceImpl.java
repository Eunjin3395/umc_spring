package umc.spring.service.ServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.AddressHandler;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.apiPayload.exception.handler.RestaurantHandler;
import umc.spring.converter.RestaurantConverter;
import umc.spring.domain.Address;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Mission;
import umc.spring.domain.restaurant.Restaurant;
import umc.spring.repository.AddressRepository;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.RestaurantRepository;
import umc.spring.service.RestaurantCommandService;
import umc.spring.web.dto.RestaurantRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantCommandServiceImpl implements RestaurantCommandService {
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public Restaurant addRestaurant(RestaurantRequestDTO.AddDto request) {

        FoodCategory foodCategory = foodCategoryRepository.findById(request.getFoodId()).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));

        //단방향인 foodCategory는 컨버터에서 설정
        Restaurant newRestaurant = RestaurantConverter.toRestaurant(request,foodCategory);

        // 양방향인 address는 여기서 설정
        Address address = addressRepository.findById(request.getAddressId()).orElseThrow(()-> new AddressHandler(ErrorStatus.ADDRESS_NOT_FOUND));
        newRestaurant.setAddress(address);

        return restaurantRepository.save(newRestaurant);
    }

    @Override
    @Transactional
    public Mission addMission(RestaurantRequestDTO.AddMissionDto request, Long restaurantId) {
        Mission mission = RestaurantConverter.toMission(request);

        //restaurantId로 엔티티 찾기
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantHandler(ErrorStatus.RESTAURANT_NOT_FOUND));

        // 양방향 매핑
        mission.setRestaurant(restaurant);

        return missionRepository.save(mission);
    }


}
