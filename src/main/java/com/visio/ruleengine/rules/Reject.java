package com.visio.ruleengine.rules;

import com.visio.ruleengine.models.Product;

import java.util.function.UnaryOperator;

public class Reject extends AbstractAction {

    @Override
    Product execute(Product product, String value) {
        product.setDisqualified(true);
        return product;
    }
}
