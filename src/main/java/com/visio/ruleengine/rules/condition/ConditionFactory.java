package com.visio.ruleengine.rules.condition;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ConditionFactory {

    private final static Map<ConditionType, Supplier<ICondition>> map = new HashMap<>();

    static {
        map.put(ConditionType.EQUALS, Equality::new);
        map.put(ConditionType.LESS_THAN, Littleness::new);
        map.put(ConditionType.GREATER_THAN, Greatness::new);
        map.put(ConditionType.EQUALS_OR_LESS_THAN, EqualityOrLittleness::new);
        map.put(ConditionType.EQUALS_OR_GREATER_THAN, EqualityOrGreatness::new);
    }

    public static ICondition createCondition(ConditionType conditionType) {
        Supplier<ICondition> iConditionSupplier = map.get(conditionType);
        if(iConditionSupplier != null) return iConditionSupplier.get();
        throw new IllegalArgumentException("No such condition " + conditionType.toString());
    }
}
