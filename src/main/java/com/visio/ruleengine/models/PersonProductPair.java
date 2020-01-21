package com.visio.ruleengine.models;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonProductPair {

    @Valid
    @NotNull(message = "Please provide a person object")
    private Person person;
    @Valid
    @NotNull(message = "Please provide a product object")
    private Product product;
}
