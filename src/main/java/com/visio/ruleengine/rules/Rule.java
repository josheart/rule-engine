package com.visio.ruleengine.rules;

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
