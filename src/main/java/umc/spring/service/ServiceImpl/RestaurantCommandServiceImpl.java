package umc.spring.service.ServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.AddressHandler;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.converter.RestaurantConverter;
import umc.spring.domain.Address;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.restaurant.Restaurant;
import umc.spring.repository.AddressRepository;
import umc.spring.repository.FoodCategoryRepository;
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

    @Override
    @Transactional
    public Restaurant addRestaurant(RestaurantRequestDTO.AddDto request) {

        Restaurant newRestaurant = RestaurantConverter.toRestaurant(request);
        Address resAddress = addressRepository.findById(request.getAddressId()).orElseThrow(()-> new AddressHandler(ErrorStatus.ADDRESS_NOT_FOUND));
        FoodCategory resFoodCategory = foodCategoryRepository.findById(request.getFoodId()).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));

        newRestaurant.setAddress(resAddress);
        newRestaurant.setFoodCategory(resFoodCategory);

        return restaurantRepository.save(newRestaurant);
    }
}
