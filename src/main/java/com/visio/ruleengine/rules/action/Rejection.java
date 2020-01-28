package com.visio.ruleengine.rules.action;

import com.visio.ruleengine.models.Product;

public class Rejection implements IAction {

    @Override
    public Product execute(Product product, String value) {
        product.setDisqualified(true);
        return product;
    }
}
