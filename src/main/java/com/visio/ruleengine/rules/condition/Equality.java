package com.visio.ruleengine.rules.condition;

import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.Product;

public class Equality implements ICondition {
    @Override
    public boolean getResult(String key, String value, Person person, Product product) {
        Object actualValue = getActual(key, person, product);
        return actualValue.toString().equals(value);
    }
}
