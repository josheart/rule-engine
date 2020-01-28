package com.visio.ruleengine.rules;

import com.visio.ruleengine.models.Action;
import com.visio.ruleengine.models.Condition;
import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.Product;
import com.visio.ruleengine.rules.action.ActionFactory;
import com.visio.ruleengine.rules.action.IAction;
import com.visio.ruleengine.rules.condition.ConditionFactory;
import com.visio.ruleengine.rules.condition.ICondition;
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
        ICondition iCondition = ConditionFactory.createCondition(this.condition.getType());
        if (iCondition.getResult(this.condition.getKey(), this.condition.getValue(), person, product)) {
            IAction iAction = ActionFactory.createAction(action.getType());
            return iAction.execute(product, action.getValue());
        }
        return product;
    }

}
