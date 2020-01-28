package com.visio.ruleengine.models;

import com.visio.ruleengine.rules.action.ActionType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Getter
@Setter
public class Action {
    private String key;
    private String value;
    private ActionType type;
}
