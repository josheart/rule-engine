package com.visio.ruleengine;

import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.PersonProductPair;
import com.visio.ruleengine.models.Product;
import com.visio.ruleengine.rules.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
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
    @DisplayName("CreditScore : 720, State : FL, Product : 7-1 ARM")
    void shouldGetProductPricing1() {
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

    @Test
    @DisplayName("CreditScore : 720, State : FL, Product : 7-1 NAVY")
    void shouldGetProductPricing2() {
        Person person = new Person(720, State.FL);
        Product product = new Product("7-1 NAVY", 5.0, false);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        Product newProduct = testRestTemplate.postForObject("http://localhost:" + port + "/productPrice",
                personProductPair, Product.class);
        assertAll(
                () -> assertTrue(newProduct.isDisqualified()),
                () -> assertEquals(4.7, newProduct.getInterest_rate())
        );
    }

    @Test
    @DisplayName("CreditScore : 719, State : FL, Product : 7-1 ARM")
    void shouldGetProductPricing3() {
        Person person = new Person(719, State.FL);
        Product product = new Product("7-1 ARM", 5.0, false);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        Product newProduct = testRestTemplate.postForObject("http://localhost:" + port + "/productPrice",
                personProductPair, Product.class);
        assertAll(
                () -> assertTrue(newProduct.isDisqualified()),
                () -> assertEquals(6.0, newProduct.getInterest_rate())
        );
    }

    @Test
    @DisplayName("CreditScore : 719, State : TX, Product : 7-1 ARM")
    void shouldGetProductPricing4() {
        Person person = new Person(719, State.TX);
        Product product = new Product("7-1 ARM", 5.0, false);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        Product newProduct = testRestTemplate.postForObject("http://localhost:" + port + "/productPrice",
                personProductPair, Product.class);
        assertAll(
                () -> assertFalse(newProduct.isDisqualified()),
                () -> assertEquals(6.0, newProduct.getInterest_rate())
        );
    }

    @Test
    @DisplayName("CreditScore : 719, State : TX, Product : 7-1 AIR")
    void shouldGetProductPricing5() {
        Person person = new Person(719, State.TX);
        Product product = new Product("7-1 AIR", 5.0, false);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        Product newProduct = testRestTemplate.postForObject("http://localhost:" + port + "/productPrice",
                personProductPair, Product.class);
        assertAll(
                () -> assertFalse(newProduct.isDisqualified()),
                () -> assertEquals(5.5, newProduct.getInterest_rate())
        );
    }

    @Test
    @DisplayName("CreditScore : 299, State : FL, Product : 7-1 ARM")
    void shouldReturnBadRequest1() {
        Person person = new Person(299, State.FL);
        Product product = new Product("7-1 ARM", 5.0, false);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        ResponseEntity<Product> responseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/productPrice",
                personProductPair, Product.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
    }

    @Test
    @DisplayName("CreditScore : 851, State : FL, Product : 7-1 ARM")
    void shouldReturnBadRequest2() {
        Person person = new Person(851, State.FL);
        Product product = new Product("7-1 ARM", 5.0, false);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        ResponseEntity<Product> responseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/productPrice",
                personProductPair, Product.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
    }

    @Test
    @DisplayName("CreditScore : 720, State : FL, Product : null")
    void shouldReturnBadRequest3() {
        Person person = new Person(720, State.FL);
        Product product = new Product(null, 5.0, false);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        ResponseEntity<Product> responseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/productPrice",
                personProductPair, Product.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
    }

    @Test
    @DisplayName("CreditScore : 720, State : null, Product : 7-1 ARM")
    void shouldReturnBadRequest4() {
        Person person = new Person(720, null);
        Product product = new Product("7-1 ARM", 5.0, false);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        ResponseEntity<Product> responseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/productPrice",
                personProductPair, Product.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
    }

    @Test
    @DisplayName("CreditScore : 720, State : FL, Product : LOWER BOUND - 3")
    void shouldReturnBadRequest5() {
        Person person = new Person(720, State.FL);
        Product product = new Product("ARM", 5.0, false);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        ResponseEntity<Product> responseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/productPrice",
                personProductPair, Product.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
    }

    @Test
    @DisplayName("CreditScore : 720, State : FL, Product : UPPER BOUND - 20")
    void shouldReturnBadRequest6() {
        Person person = new Person(720, State.FL);
        Product product = new Product("it is not a valid state", 5.0, false);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        ResponseEntity<Product> responseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/productPrice",
                personProductPair, Product.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
    }

    @Test
    @DisplayName("No Person, Product : 7-1 ARM")
    void shouldReturnBadRequest7() {
        Product product = new Product("it is not a valid state", 5.0, false);
        PersonProductPair personProductPair = new PersonProductPair(null, product);
        ResponseEntity<Product> responseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/productPrice",
                personProductPair, Product.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
    }

    @Test
    @DisplayName("CreditScore : 720, State : FL, Product : No Product")
    void shouldReturnBadRequest8() {
        Person person = new Person(720, State.FL);
        PersonProductPair personProductPair = new PersonProductPair(person, null);
        ResponseEntity<Product> responseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/productPrice",
                personProductPair, Product.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
    }




}
