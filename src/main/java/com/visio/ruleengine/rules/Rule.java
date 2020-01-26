package com.visio.ruleengine.rules;

import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.Product;

@FunctionalInterface
public interface Rule<T,R> {
    /**
     *
     * @param r
     * @param t
     * @return
     */
    T applyTo(R r, T t);
}
