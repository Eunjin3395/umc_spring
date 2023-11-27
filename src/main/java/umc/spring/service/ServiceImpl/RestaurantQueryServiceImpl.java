package umc.spring.service.ServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.repository.RestaurantRepository;
import umc.spring.service.RestaurantQueryService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantQueryServiceImpl implements RestaurantQueryService {
    private final RestaurantRepository restaurantRepository;
    @Override
    public boolean isExistRestaurant(Long id) {
        return restaurantRepository.existsById(id);
    }
}
