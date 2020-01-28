package com.visio.ruleengine.rules.action;

import com.visio.ruleengine.models.Product;

@FunctionalInterface
public interface IAction {
    /**
     *
     * @param product
     * @param value
     * @return
     */
    Product execute(Product product, String value);
}
