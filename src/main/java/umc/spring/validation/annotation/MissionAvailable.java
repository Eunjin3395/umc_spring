package umc.spring.validation.annotation;

import umc.spring.validation.validator.MissionAvailableValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MissionAvailableValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MissionAvailable {
    String message() default "이미 진행 중인 미션입니다.";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};
}
