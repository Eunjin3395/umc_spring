package umc.spring.service.FoodCategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.FoodCategory;
import umc.spring.repository.FoodCategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodCategoryServiceImpl implements FoodCategoryService {

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public boolean isExistFoodCategory(Long id) {
        return foodCategoryRepository.existsById(id);
    }
}
