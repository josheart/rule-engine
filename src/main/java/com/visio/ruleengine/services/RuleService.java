package com.visio.ruleengine.services;

import com.visio.ruleengine.models.PersonProductPair;
import com.visio.ruleengine.models.Product;

public interface RuleService {
    Product getProductPricing(PersonProductPair personProductPair);
}
