package com.visio.ruleengine.rules;

import com.visio.ruleengine.models.Product;
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
