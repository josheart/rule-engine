package com.visio.ruleengine.rules;

import com.visio.ruleengine.models.Product;
import lombok.Getter;
import lombok.Setter;


public abstract class AbstractAction{

    abstract Product execute(Product product, String value);
}
