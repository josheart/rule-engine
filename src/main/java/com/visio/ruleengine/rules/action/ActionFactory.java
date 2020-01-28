package com.visio.ruleengine.rules.action;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ActionFactory {

    final static Map<ActionType, Supplier<IAction>> map = new HashMap<>();

    static {
        map.put(ActionType.DECREASE, Decrement::new);
        map.put(ActionType.INCREASE, Increment::new);
        map.put(ActionType.REJECT, Rejection::new);
    }

    /**
     *
     * @param actionType
     * @return
     */
   public static IAction createAction(ActionType actionType) {
      Supplier<IAction> p = map.get(actionType);
      if (p != null) return p.get();
      throw new IllegalArgumentException("No such action : " + actionType.toString());
    }
}
