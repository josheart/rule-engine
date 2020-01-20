package com.visio.ruleengine.repositories;

import com.visio.ruleengine.engine.Rule;
import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.Product;

import java.util.List;

public interface RuleEngine {

    default Product applyRules(Person person, Product product, List<Rule> rules){
        Product result = product;
        for (Rule rule : rules)
        {
            result = rule.applyTo(person, result);
        }
        return result;
    }
}
