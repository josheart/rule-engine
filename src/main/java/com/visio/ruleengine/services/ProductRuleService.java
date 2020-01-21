package com.visio.ruleengine.services;

import com.visio.ruleengine.RuleInit;
import com.visio.ruleengine.models.PersonProductPair;
import com.visio.ruleengine.models.Product;
import com.visio.ruleengine.engines.RuleEngine;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductRuleService implements RuleService {
    private static final Logger LOG = LoggerFactory.getLogger(ProductRuleService.class);

    private final RuleInit ruleInit;

    private final RuleEngine ruleEngine;

    @Override
    public Product getProductPricing(PersonProductPair personProductPair){
        return ruleEngine.applyRules(personProductPair.getPerson(), personProductPair.getProduct(), ruleInit.getRules());
    }

}
