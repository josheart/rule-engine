package com.visio.ruleengine.rules;

import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.Product;

public class ProductRule implements Rule {

    private Action action;
    private Condition condition;

    @Override
    public Product applyTo(Person person, Product product) {
        if (condition.getResult(person, product)){
            return action.execute(product);
        }
        return product;
    }

}
