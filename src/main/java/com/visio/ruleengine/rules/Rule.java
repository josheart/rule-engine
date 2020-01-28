package com.visio.ruleengine.rules;

@FunctionalInterface
public interface Rule<T, R> {
    T applyTo(R r, T t);
}
