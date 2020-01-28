package com.visio.ruleengine.engines;

import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.Product;
import com.visio.ruleengine.rules.Rule;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductRuleEngine implements RuleEngine {
    private static final Logger LOG = LoggerFactory.getLogger(ProductRuleEngine.class);
    /**
     * @param person
     * @param product
     * @param rules business rules to decide product eligibility and interest rate
     * @return the productPrice and eligibility
     */
    @Override
    public Product applyRules(Person person, Product product, List<Rule<Product, Person>> rules) {
        LOG.info("Rules are being applied to applicant...");
        Product result = product;
        for (Rule<Product, Person> rule : rules) {
            result = rule.applyTo(person, result);
        }
        return result;
    }
}
