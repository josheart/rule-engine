package com.visio.ruleengine.models;

import com.visio.ruleengine.rules.condition.ConditionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Condition {
    private String key;
    private String value;
    private ConditionType type;
}
