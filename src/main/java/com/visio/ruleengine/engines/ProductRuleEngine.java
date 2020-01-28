package com.visio.ruleengine.engines;

import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.Product;
import com.visio.ruleengine.rules.Rule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductRuleEngine implements RuleEngine {
    /**
     * @param person
     * @param product
     * @param rules   business rules to decide product eligibility and interest rate
     * @return the productPrice and eligibility
     */
    @Override
    public Product applyRules(Person person, Product product, List<Rule<Product, Person>> rules) {
        Product result = product;
        for (Rule<Product, Person> rule : rules) {
            result = rule.applyTo(person, result);
        }
        return result;
    }
}
