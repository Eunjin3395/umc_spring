package umc.spring.service.ServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.restaurant.Restaurant;
import umc.spring.domain.review.Review;
import umc.spring.repository.RestaurantRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.service.RestaurantQueryService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantQueryServiceImpl implements RestaurantQueryService {
    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;
    @Override
    public boolean isExistRestaurant(Long id) {
        return restaurantRepository.existsById(id);
    }

    @Override
    public Optional<Restaurant> findRestaurant(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Page<Review> getReviewList(Long restaurantId, Integer page) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        Page<Review> RestaurantPage = reviewRepository.findAllByRestaurant(restaurant, PageRequest.of(page,10));
        return RestaurantPage;
    }
}
