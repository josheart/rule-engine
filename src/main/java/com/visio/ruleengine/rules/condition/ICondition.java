package com.visio.ruleengine.rules.condition;

import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.Product;

import java.lang.reflect.Field;

@FunctionalInterface
public interface ICondition {

    boolean getResult(String key, String value, Person person, Product product);

    /**
     * @param key     the value defined in the rules
     * @param person
     * @param product
     * @return the provided data to be evaluated
     */
    default Object getActual(String key, Person person, Product product) {
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
    default boolean equals(Object actualValue, String value) {
        return Double.parseDouble(actualValue.toString()) == Double.parseDouble(value);
    }

    /**
     * @param actualValue
     * @param value
     * @return
     */
    default boolean greaterThan(Object actualValue, String value) {
        return Double.parseDouble(actualValue.toString()) > Double.parseDouble(value);
    }

    /**
     * @param actualValue
     * @param value
     * @return
     */
    default boolean lessThan(Object actualValue, String value) {
        return Double.parseDouble(actualValue.toString()) < Double.parseDouble(value);
    }
}
