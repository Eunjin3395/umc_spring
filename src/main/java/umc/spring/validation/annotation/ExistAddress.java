package umc.spring.validation.annotation;

import umc.spring.validation.validator.AddressExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AddressExistValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistAddress {
    String message() default "해당하는 지역 정보가 존재하지 않습니다.";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};
}
