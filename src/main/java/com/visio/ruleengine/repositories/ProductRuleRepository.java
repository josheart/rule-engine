package com.visio.ruleengine.repositories;

import com.visio.ruleengine.engine.Rule;
import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.Product;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.List;

@Getter
@Repository
public class ProductRuleRepository {
    private static final Logger LOG = LoggerFactory.getLogger(ProductRuleRepository.class);

    public Product applyRules(Person person, Product product, List<Rule> rules){
        Product result = product;
        for (Rule rule : rules)
        {
            result = rule.applyTo(person, result);
        }
        return result;
    }

}
