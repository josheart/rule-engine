package com.visio.ruleengine.views;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CreditScoreConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CreditScore {

    String message() default "Credit score should be between 300 and 850";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
