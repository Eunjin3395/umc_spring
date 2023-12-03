package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.restaurant.Restaurant;
import umc.spring.domain.review.Review;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    Page<Review> findAllByRestaurant(Restaurant restaurant, PageRequest pageRequest);

}
