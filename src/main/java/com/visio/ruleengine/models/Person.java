package com.visio.ruleengine.models;

import com.visio.ruleengine.engine.State;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private int credit_score;
    private State state;
}
