package com.visio.ruleengine.models;


import com.visio.ruleengine.rules.State;
import com.visio.ruleengine.views.CreditScore;
import lombok.*;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @CreditScore
    @NotNull(message = "Please provide a valid credit_score")
    private int credit_score;
    @NotNull
    private State state;

}
