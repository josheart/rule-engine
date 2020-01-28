package com.visio.ruleengine.models;

import com.visio.ruleengine.rules.action.ActionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 */
@Getter
@Setter
@ToString
public class Action {
    private String key;
    private String value;
    private ActionType type;
}
