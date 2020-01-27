package com.visio.ruleengine.rules;

import com.visio.ruleengine.models.Product;

public interface IAction {
    Product execute(Product product, String value);
}
