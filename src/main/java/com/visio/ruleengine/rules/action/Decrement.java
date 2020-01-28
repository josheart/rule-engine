package com.visio.ruleengine.rules.action;

import com.visio.ruleengine.models.Product;

public class Decrement implements IAction {

    @Override
    public Product execute(Product product, String value) {
        product.setInterest_rate(product.getInterest_rate() - Double.parseDouble(value));
        return product;
    }
}
