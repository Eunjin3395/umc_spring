package umc.spring.service.ServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.service.FoodCategoryService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodCategoryServiceImpl implements FoodCategoryService {

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public boolean isExistFoodCategory(Long id) {
        return foodCategoryRepository.existsById(id);
    }
}
