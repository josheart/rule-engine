package com.visio.ruleengine.engine;

import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.Product;

import java.lang.reflect.Field;

public class Condition {
    private String key;
    private String value;
    private ConditionType type;

    public boolean getResult(Person person, Product product){
        Object actualValue = getActual(key, person, product);
        switch (type){
            case EQUALS:
                return actualValue.toString().equals(value);
            case LESS_THAN:
                return lessThan(actualValue, value);
            case GREATER_THAN:
                return greaterThan(actualValue, value);
            case EQUALS_OR_LESS_THAN:
                return equals(actualValue, value) || lessThan(actualValue, value);
            case EQUALS_OR_GREATER_THAN:
                return equals(actualValue, value) || greaterThan(actualValue, value);
        }
        return false;
    }

    private Object getActual(String key, Person person, Product product) {
        try {
            Field[] fields = person.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals(key)){
                    return field.get(person);
                }
            }
            fields = product.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals(key)){
                    return field.get(product);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean equals(Object actualValue, String value) {
        return Double.parseDouble(actualValue.toString()) == Double.parseDouble(value);
    }

    private boolean greaterThan(Object actualValue, String value) {
        return Double.parseDouble(actualValue.toString()) > Double.parseDouble(value);
    }

    private boolean lessThan(Object actualValue, String value) {
        return Double.parseDouble(actualValue.toString()) < Double.parseDouble(value);
    }
}
