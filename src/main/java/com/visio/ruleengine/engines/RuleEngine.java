package com.visio.ruleengine.engines;

import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.Product;
import com.visio.ruleengine.rules.Rule;

import java.util.List;

@FunctionalInterface
public interface RuleEngine {
    Product applyRules(Person person, Product product, List<Rule<Product, Person>> rules);
}
