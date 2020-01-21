package com.visio.ruleengine.models;


import com.visio.ruleengine.rules.State;
import com.visio.ruleengine.views.CreditScore;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @NotNull(message = "Please provide a credit score")
    @CreditScore
    private int credit_score;
    @NotNull
    private State state;
}
