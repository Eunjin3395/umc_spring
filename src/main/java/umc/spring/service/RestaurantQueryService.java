package umc.spring.service;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.restaurant.Restaurant;
import umc.spring.domain.review.Review;

import java.util.Optional;

public interface RestaurantQueryService {
    public boolean isExistRestaurant(Long id);

    Optional<Restaurant> findRestaurant(Long id);

    Page<Review> getReviewList(Long restaurantId, Integer page);

    Page<Mission> getMissionList(Long restaurantId, Integer page);
}
