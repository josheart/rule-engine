package com.visio.ruleengine.rules;

import com.visio.ruleengine.models.Product;

public class Reject extends IAction {

    @Override
    public Product execute(Product product, String value) {
        product.setDisqualified(true);
        return product;
    }
}
