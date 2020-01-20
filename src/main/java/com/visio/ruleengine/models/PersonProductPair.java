package com.visio.ruleengine.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonProductPair {
    private Person person;
    private Product product;
}
