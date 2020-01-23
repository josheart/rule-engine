package com.visio.ruleengine.views;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = InterestRateConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InterestRate {

    String message() default "Given initial interest rate is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
