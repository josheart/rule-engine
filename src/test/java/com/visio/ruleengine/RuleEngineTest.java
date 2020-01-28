package com.visio.ruleengine;

import com.visio.ruleengine.engines.RuleEngine;
import com.visio.ruleengine.models.Action;
import com.visio.ruleengine.models.Condition;
import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.Product;
import com.visio.ruleengine.rules.ProductRule;
import com.visio.ruleengine.rules.Rule;
import com.visio.ruleengine.rules.action.ActionType;
import com.visio.ruleengine.rules.condition.ConditionType;
import com.visio.ruleengine.rules.condition.State;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RuleEngineTest {

    /**
     * The engine that we want to test
     */
    @Autowired
    RuleEngine ruleEngine;

    List<Rule<Product, Person>> rules;

    @BeforeAll
    void setup() {
        rules = new ArrayList<>();

        ProductRule firstProductRule = new ProductRule();
        Condition firstCondition = new Condition();
        firstCondition.setKey("state");
        firstCondition.setType(ConditionType.EQUALS);
        firstCondition.setValue("FL");
        Action firstAction = new Action();
        firstAction.setType(ActionType.REJECT);
        firstProductRule.setCondition(firstCondition);
        firstProductRule.setAction(firstAction);

        ProductRule secondProductRule = new ProductRule();
        Condition secondCondition = new Condition();
        secondCondition.setKey("credit_score");
        secondCondition.setType(ConditionType.LESS_THAN);
        secondCondition.setValue("720");
        Action secondAction = new Action();
        secondAction.setKey("interest_rate");
        secondAction.setType(ActionType.INCREASE);
        secondAction.setValue("0.5");
        secondProductRule.setCondition(secondCondition);
        secondProductRule.setAction(secondAction);

        ProductRule thirdProductRule = new ProductRule();
        Condition thirdCondition = new Condition();
        thirdCondition.setKey("credit_score");
        thirdCondition.setType(ConditionType.EQUALS_OR_GREATER_THAN);
        thirdCondition.setValue("720");
        Action thirdAction = new Action();
        thirdAction.setKey("interest_rate");
        thirdAction.setType(ActionType.DECREASE);
        thirdAction.setValue("0.3");
        thirdProductRule.setCondition(thirdCondition);
        thirdProductRule.setAction(thirdAction);

        ProductRule fourthProductRule = new ProductRule();
        Condition forthCondition = new Condition();
        forthCondition.setKey("name");
        forthCondition.setType(ConditionType.EQUALS);
        forthCondition.setValue("7-1 ARM");
        Action forthAction = new Action();
        forthAction.setKey("interest_rate");
        forthAction.setType(ActionType.INCREASE);
        forthAction.setValue("0.5");
        fourthProductRule.setCondition(forthCondition);
        fourthProductRule.setAction(forthAction);

        ProductRule fifthProductRule = new ProductRule();
        Condition fifthCondition = new Condition();
        fifthCondition.setKey("term");
        fifthCondition.setType(ConditionType.EQUALS_OR_GREATER_THAN);
        fifthCondition.setValue("60");
        Action fifthAction = new Action();
        fifthAction.setKey("interest_rate");
        fifthAction.setType(ActionType.INCREASE);
        fifthAction.setValue("0.5");
        fifthProductRule.setCondition(fifthCondition);
        fifthProductRule.setAction(fifthAction);

        ProductRule sixthProductRule = new ProductRule();
        Condition sixthCondition = new Condition();
        sixthCondition.setKey("term");
        sixthCondition.setType(ConditionType.LESS_THAN);
        sixthCondition.setValue("60");
        Action sixthAction = new Action();
        sixthAction.setKey("interest_rate");
        sixthAction.setType(ActionType.DECREASE);
        sixthAction.setValue("0.2");
        sixthProductRule.setCondition(sixthCondition);
        sixthProductRule.setAction(sixthAction);

        rules.add(firstProductRule);
        rules.add(secondProductRule);
        rules.add(thirdProductRule);
        rules.add(fourthProductRule);
        rules.add(fifthProductRule);
        rules.add(sixthProductRule);
    }

    @Test
    @DisplayName("CreditScore : 720, State : FL, Product : 7-1 ARM, term : 60")
    void shouldApplyRule1() {
        Person person = new Person(720, State.FL);
        Product product = new Product("7-1 ARM", 5.0, false, 60);
        Product postProduct = ruleEngine.applyRules(person, product, rules);
        assertAll(
                () -> assertTrue(postProduct.isDisqualified()),
                () -> assertEquals(5.7, postProduct.getInterest_rate())
        );
    }

    @Test
    @DisplayName("CreditScore : 720, State : FL, Product : 5-1 ARM, term : 60")
    void shouldApplyRule2() {
        Person person = new Person(720, State.FL);
        Product product = new Product("5-1 ARM", 5.0, false, 60);
        Product postProduct = ruleEngine.applyRules(person, product, rules);
        assertAll(
                () -> assertTrue(postProduct.isDisqualified()),
                () -> assertEquals(5.2, postProduct.getInterest_rate())
        );
    }

    @Test
    @DisplayName("CreditScore : 719, State : FL, Product : 7-1 ARM, term : 60")
    void shouldApplyRule3() {
        Person person = new Person(719, State.FL);
        Product product = new Product("7-1 ARM", 5.0, false, 60);
        Product postProduct = ruleEngine.applyRules(person, product, rules);
        assertAll(
                () -> assertTrue(postProduct.isDisqualified()),
                () -> assertEquals(6.5, postProduct.getInterest_rate())
        );
    }

    @Test
    @DisplayName("CreditScore : 719, State : TX, Product : 7-1 ARM, term : 60")
    void shouldApplyRule4() {
        Person person = new Person(719, State.TX);
        Product product = new Product("7-1 ARM", 5.0, false, 60);
        Product postProduct = ruleEngine.applyRules(person, product, rules);
        assertAll(
                () -> assertFalse(postProduct.isDisqualified()),
                () -> assertEquals(6.5, postProduct.getInterest_rate())
        );
    }

    @Test
    @DisplayName("CreditScore : 719, State : TX, Product : 30-YR FIXED, term : 60")
    void shouldApplyRule5() {
        Person person = new Person(719, State.TX);
        Product product = new Product("30-YR FIXED", 5.0, false, 60);
        Product postProduct = ruleEngine.applyRules(person, product, rules);
        assertAll(
                () -> assertFalse(postProduct.isDisqualified()),
                () -> assertEquals(6.0, postProduct.getInterest_rate())
        );
    }

    @Test
    @DisplayName("CreditScore : 719, State : TX, Product : 5-1 ARM, term : 48")
    void shouldApplyRule6() {
        Person person = new Person(719, State.TX);
        Product product = new Product("5-1 ARM", 5.0, false, 48);
        Product postProduct = ruleEngine.applyRules(person, product, rules);
        assertAll(
                () -> assertFalse(postProduct.isDisqualified()),
                () -> assertEquals(5.3, postProduct.getInterest_rate())
        );
    }

    @ParameterizedTest
    @EnumSource(value = State.class, names = {"FL"}, mode = EnumSource.Mode.EXCLUDE)
    @DisplayName("Only FL is not eligible")
    void shouldProductBeGranted(State state) {
        Person person = new Person(720, state);
        Product product = new Product("7-1 ARM", 5.0, false, 60);
        Product postProduct = ruleEngine.applyRules(person, product, rules);
        assertAll(
                () -> assertFalse(postProduct.isDisqualified()),
                () -> assertEquals(5.7, postProduct.getInterest_rate())
        );
    }
}
