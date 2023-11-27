package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.FoodCategoryService;
import umc.spring.validation.annotation.ExistCategories;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class CategoryExistValidator implements ConstraintValidator<ExistCategories,Long> {
    private final FoodCategoryService foodCategoryService;
    @Override
    public void initialize(ExistCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        boolean isValid = foodCategoryService.isExistFoodCategory(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_CATEGORY_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
