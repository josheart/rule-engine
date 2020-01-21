package com.visio.ruleengine.models;

import com.visio.ruleengine.views.CreditScore;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Data {

    @CreditScore
    @NotNull(message = "Please provide a credit score")
    private int credit_score;

    @NotNull
    private String state;

    @Size(min = 4, max = 25)
    @NotNull(message = "Please provide a valid product name")
    private String productName;

    @NotNull
    private double interest_rate;

    private boolean disqualified;

}
