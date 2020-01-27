package com.visio.ruleengine.engines;

import com.visio.ruleengine.rules.Rule;
import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.Product;

import java.util.List;

public interface RuleEngine {
    Product applyRules(Person person, Product product, List<Rule<Product, Person>> rules);
}
