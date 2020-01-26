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
            IAction iAction = ActionFactory.createAction(action.getType());
            return iAction.execute(product, action.getValue());
        }
        return product;
    }

}
