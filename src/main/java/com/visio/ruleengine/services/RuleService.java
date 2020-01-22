package com.visio.ruleengine.services;

import com.visio.ruleengine.models.PersonProductPair;
import com.visio.ruleengine.models.Product;
import com.visio.ruleengine.rules.Rule;

import java.util.List;
import java.util.Optional;

public interface RuleService {
    Product applyRules(PersonProductPair personProductPair, List<Rule> rules);
}
