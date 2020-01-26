package com.visio.ruleengine.rules;

import java.util.function.UnaryOperator;

import static com.visio.ruleengine.rules.ActionType.LOWER;

public class ActionFactory {

    public static AbstractAction createAction(ActionType actionType) {
        switch (actionType) {
            case LOWER:
                return new Lower();
            case RAISE:
                return new Raise();
            case REJECT:
                return new Reject();
            default:
                return null;
        }
    }
}
