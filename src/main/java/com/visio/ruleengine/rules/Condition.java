package com.visio.ruleengine.rules;

import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;

@Getter
@Setter
public class Condition {
    private String key;
    private String value;
    private ConditionType type;

    /**
     * @param person
     * @param product
     * @return
     */
    public boolean getResult(Person person, Product product) {
        Object actualValue = getActual(key, person, product);
        switch (type) {
            case EQUALS:
                assert actualValue != null;
                return actualValue.toString().equals(value);
            case LESS_THAN:
                assert actualValue != null;
                return lessThan(actualValue, value);
            case GREATER_THAN:
                assert actualValue != null;
                return greaterThan(actualValue, value);
            case EQUALS_OR_LESS_THAN:
                assert actualValue != null;
                return equals(actualValue, value) || lessThan(actualValue, value);
            case EQUALS_OR_GREATER_THAN:
                assert actualValue != null;
                return equals(actualValue, value) || greaterThan(actualValue, value);
        }
        return false;
    }

    /**
     * @param key the value defined in the rules
     * @param person
     * @param product
     * @return the provided data to be evaluated
     */
    private Object getActual(String key, Person person, Product product) {
        try {
            Field[] fields = person.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals(key)) {
                    return field.get(person);
                }
            }
            fields = product.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals(key)) {
                    return field.get(product);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param actualValue
     * @param value
     * @return
     */
    private boolean equals(Object actualValue, String value) {
        return Double.parseDouble(actualValue.toString()) == Double.parseDouble(value);
    }

    /**
     * @param actualValue
     * @param value
     * @return
     */
    private boolean greaterThan(Object actualValue, String value) {
        return Double.parseDouble(actualValue.toString()) > Double.parseDouble(value);
    }

    /**
     * @param actualValue
     * @param value
     * @return
     */
    private boolean lessThan(Object actualValue, String value) {
        return Double.parseDouble(actualValue.toString()) < Double.parseDouble(value);
    }
}
