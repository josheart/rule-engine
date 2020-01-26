package com.visio.ruleengine.rules;

import com.visio.ruleengine.models.Product;

public class Raise implements IAction {

    @Override
    public Product execute(Product product, String value) {
        product.setInterest_rate(product.getInterest_rate() + Double.parseDouble(value));
        return product;
    }
}
