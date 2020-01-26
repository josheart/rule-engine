package com.visio.ruleengine.rules;

import com.visio.ruleengine.models.Product;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Getter
@Setter
public class Action {
    private String key;
    private String value;
    private ActionType type;

    Product execute(Product product) {
        switch (type) {
            case LOWER:
                product.setInterest_rate(product.getInterest_rate() - Double.parseDouble(value));
                return product;
            case RAISE:
                product.setInterest_rate(product.getInterest_rate() + Double.parseDouble(value));
                return product;
            case REJECT:
                product.setDisqualified(true);
                return product;
            case DIVIDE_BY:
                product.setInterest_rate(product.getInterest_rate() / Double.parseDouble(value));
                return product;
            case MULTIPLY_BY:
                product.setInterest_rate(product.getInterest_rate() * Double.parseDouble(value));
                return product;
            default:
                return product;
        }
    }
}
