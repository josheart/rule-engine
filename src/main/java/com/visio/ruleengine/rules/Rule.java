package com.visio.ruleengine.rules;

import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.Product;

@FunctionalInterface
public interface Rule {
    /**
     *
     * @param person
     * @param product
     * @return
     */
    Product applyTo(Person person, Product product);
}
