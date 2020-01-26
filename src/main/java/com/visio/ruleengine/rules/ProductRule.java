package com.visio.ruleengine.rules;

import com.visio.ruleengine.models.Action;
import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.Product;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductRule implements Rule<Product, Person> {

    private Action action;

    private Condition condition;

    /**
     * @param person
     * @param product
     * @return
     */
    @Override
    public Product applyTo(Person person, Product product) {
        if (condition.getResult(person, product)) {
            AbstractAction abstractAction = ActionFactory.createAction(action.getType());
            return abstractAction.execute(product, action.getValue());
        }
        return product;
    }

}
