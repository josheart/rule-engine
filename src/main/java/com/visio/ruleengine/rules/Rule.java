package com.visio.ruleengine.rules;

import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.Product;

@FunctionalInterface
public interface Rule {
    Product applyTo(Person person, Product product);
}
