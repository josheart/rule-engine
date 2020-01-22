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

    public Product execute(Product product) {
        switch (type) {
            case LOWER:
                return math(product, "-", value);
            case GRANT:
                return product;
            case RAISE:
                return math(product, "+", value);
            case REJECT:
                product.setDisqualified(true);
                return product;
            case DIVIDE_BY:
                return math(product, "*", value);
            case MULTIPLY_BY:
                return math(product, "/", value);
        }
        return product;
    }

    private Product math(Product product, String operand, String value) {
        switch (operand) {
            case "+":
                product.setInterest_rate(product.getInterest_rate() + Double.parseDouble(value));
                return product;
            case "-":
                product.setInterest_rate(product.getInterest_rate() - Double.parseDouble(value));
                return product;
            case "*":
                product.setInterest_rate(product.getInterest_rate() * Double.parseDouble(value));
                return product;
            case "/":
                product.setInterest_rate(product.getInterest_rate() / Double.parseDouble(value));
                return product;
        }
        return product;
    }
}
