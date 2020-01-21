package com.visio.ruleengine.engine;

import com.visio.ruleengine.models.Data;
import lombok.AllArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PriceRuleEngine implements Engine {

    private KieSession kieSession;

    @Override
    public Data applyRules(Data data) {
        kieSession.insert(data);
        kieSession.fireAllRules();
        return data;
    }
}
