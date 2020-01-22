package com.visio.ruleengine;

import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.PersonProductPair;
import com.visio.ruleengine.models.Product;
import com.visio.ruleengine.rules.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void shouldGetProductPricing() {
        Person person = new Person(720, State.FL);
        Product product = new Product("7-1 ARM", 5.0, false);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        Product newProduct = testRestTemplate.postForObject("http://localhost:" + port + "/productPrice",
                personProductPair, Product.class);
        assertAll(
                () -> assertTrue(newProduct.isDisqualified()),
                () -> assertEquals(5.2, newProduct.getInterest_rate())
        );
    }


}
