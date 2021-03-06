package com.visio.ruleengine.views;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CreditScoreConstraintValidator implements ConstraintValidator<CreditScore, Integer> {

    @Override
    public void initialize(CreditScore creditScore) {

    }

    @Override
    public boolean isValid(Integer creditScoreField, ConstraintValidatorContext cxt) {
        return creditScoreField >= 300 && creditScoreField <= 850;
    }
}
