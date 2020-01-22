package com.visio.ruleengine.services;

import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.PersonProductPair;
import com.visio.ruleengine.models.Product;
import com.visio.ruleengine.engines.RuleEngine;
import com.visio.ruleengine.rules.Rule;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductRuleService implements RuleService {
    private static final Logger LOG = LoggerFactory.getLogger(ProductRuleService.class);

    private final RuleEngine ruleEngine;

    @Override
    public Product applyRules(PersonProductPair personProductPair, List<Rule> rules){
        Person person = personProductPair.getPerson();
        Product product = personProductPair.getProduct();
        return ruleEngine.applyRules(person, product, rules);
    }

}
