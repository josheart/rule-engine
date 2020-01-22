package com.visio.ruleengine;

import com.visio.ruleengine.engines.RuleEngine;
import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.PersonProductPair;
import com.visio.ruleengine.models.Product;
import com.visio.ruleengine.rules.*;
import com.visio.ruleengine.services.RuleService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RuleServiceTest {
    /**
     * The service that we want to test
     */
    @Autowired
    private RuleService ruleService;

    /**
     * A mock version of the RuleEngine for use in our tests
     */
    @MockBean
    private RuleEngine ruleEngine;

    /**
     * A mock version of the RuleInit for use in our tests
     */

    @Test
    @DisplayName("Test applyRules - Success")
    void shouldApplyRules() {
        Person person = new Person(720, State.FL);
        Product product = new Product("7-1 ARM", 5.0, false);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        Product mockProduct = new Product("7-1 ARM", 5.2, true);
        doReturn(mockProduct).when(ruleEngine).applyRules(person, product, null);

        Product returnedProduct = ruleService.applyRules(personProductPair, null);
        Assertions.assertSame(returnedProduct, mockProduct, "Products should be the same");
    }


}
