package com.visio.ruleengine.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String name;
    private double interest_rate;
    private boolean isDisqualified;
}
