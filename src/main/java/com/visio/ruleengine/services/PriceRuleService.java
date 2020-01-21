package com.visio.ruleengine.services;

import com.visio.ruleengine.engine.Engine;
import com.visio.ruleengine.models.Data;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PriceRuleService implements RuleService {

    private Engine engine;

    @Override
    public Data getProductPricing(Data data) {
        engine.applyRules(data);
        return data;
    }
}
