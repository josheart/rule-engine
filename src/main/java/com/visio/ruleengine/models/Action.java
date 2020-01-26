package com.visio.ruleengine.models;

import com.visio.ruleengine.models.Product;
import com.visio.ruleengine.rules.ActionType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Getter
@Setter
public class Action {
    public String key;
    public String value;
    public ActionType type;
}
