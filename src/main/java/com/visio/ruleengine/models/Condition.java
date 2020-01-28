package com.visio.ruleengine.models;

import com.visio.ruleengine.rules.condition.ConditionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Condition {
    private String key;
    private String value;
    private ConditionType type;
}
