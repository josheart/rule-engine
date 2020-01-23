package com.visio.ruleengine.views;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InterestRateConstraintValidator implements ConstraintValidator<InterestRate, Double> {

    @Value("${rule.interestrate}")
    private double interestRate;

    @Override
    public void initialize(InterestRate constraintAnnotation) {

    }

    @Override
    public boolean isValid(Double interestRateField, ConstraintValidatorContext constraintValidatorContext) {
        return interestRateField == interestRate;
    }
}
