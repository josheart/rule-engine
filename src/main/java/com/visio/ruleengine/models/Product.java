package com.visio.ruleengine.models;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @NotNull(message = "Please provide a valid product name")
    @Size(min = 4, max = 25)
    private String name;
    @NotNull
    private double interest_rate;
    private boolean isDisqualified;
}
