package com.visio.ruleengine.rules;

import com.visio.ruleengine.models.Action;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

class ActionFactory {

   static IAction createAction(ActionType actionType) {
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
