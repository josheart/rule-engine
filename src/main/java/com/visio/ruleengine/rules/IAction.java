package com.visio.ruleengine.rules;

import com.visio.ruleengine.models.Product;


public abstract class IAction {

    abstract Product execute(Product product, String value);
}
