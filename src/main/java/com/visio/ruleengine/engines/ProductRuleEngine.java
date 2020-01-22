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
     * @param rules
     * @return
     */
    @Override
    public Product applyRules(Person person, Product product, List<Rule> rules) {

        Product result = product;
        for (Rule rule : rules) {
            result = rule.applyTo(person, result);
        }
        return result;
    }
}
