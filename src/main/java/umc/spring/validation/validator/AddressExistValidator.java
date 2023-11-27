package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.AddressQueryService;
import umc.spring.validation.annotation.ExistAddress;
import umc.spring.validation.annotation.ExistCategories;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class AddressExistValidator implements ConstraintValidator<ExistAddress, Long> {
    private final AddressQueryService addressQueryService;

    @Override
    public void initialize(ExistAddress constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        boolean isValid = addressQueryService.isExistAddress(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.ADDRESS_NOT_FOUND.toString()).addConstraintViolation();
        }
        return isValid;
    }
}
