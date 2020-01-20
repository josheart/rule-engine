package com.visio.ruleengine.services;

import com.visio.ruleengine.RuleInit;
import com.visio.ruleengine.repositories.ProductRuleRepository;
import com.visio.ruleengine.models.PersonProductPair;
import com.visio.ruleengine.models.Product;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductRuleService {
    private static final Logger LOG = LoggerFactory.getLogger(ProductRuleService.class);

    private final RuleInit ruleInit;

    private final ProductRuleRepository productRuleRepository;

    public Product getProductPricing(PersonProductPair personProductPair){
        return productRuleRepository.applyRules(personProductPair.getPerson(), personProductPair.getProduct(), ruleInit.getRules());
    }

}
