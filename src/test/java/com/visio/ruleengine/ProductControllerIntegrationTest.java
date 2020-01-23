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
    @DisplayName("POST /product-price - CreditScore : 720, State : FL, ProductName : 7-1 ARM, Term : 60")
    void shouldGetProductPricing1() {
        Person person = new Person(720, State.FL);
        Product product = new Product("7-1 ARM", 5.0, false, 60);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        Product newProduct = testRestTemplate.postForObject("http://localhost:" + port + "/product-price",
                personProductPair, Product.class);
        assertAll(
                () -> assertTrue(newProduct.isDisqualified()),
                () -> assertEquals(5.7, newProduct.getInterest_rate())
        );
    }

    @Test
    @DisplayName("POST /product-price - CreditScore : 720, State : FL, ProductName : 7-1 NAVY, Term : 60")
    void shouldGetProductPricing2() {
        Person person = new Person(720, State.FL);
        Product product = new Product("7-1 NAVY", 5.0, false, 60);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        Product newProduct = testRestTemplate.postForObject("http://localhost:" + port + "/product-price",
                personProductPair, Product.class);
        assertAll(
                () -> assertTrue(newProduct.isDisqualified()),
                () -> assertEquals(5.2, newProduct.getInterest_rate())
        );
    }

    @Test
    @DisplayName("POST /product-price - CreditScore : 719, State : FL, ProductName : 7-1 ARM, Term : 60")
    void shouldGetProductPricing3() {
        Person person = new Person(719, State.FL);
        Product product = new Product("7-1 ARM", 5.0, false, 60);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        Product newProduct = testRestTemplate.postForObject("http://localhost:" + port + "/product-price",
                personProductPair, Product.class);
        assertAll(
                () -> assertTrue(newProduct.isDisqualified()),
                () -> assertEquals(6.5, newProduct.getInterest_rate())
        );
    }

    @Test
    @DisplayName("POST /product-price - CreditScore : 719, State : TX, ProductName : 7-1 ARM, Term : 60")
    void shouldGetProductPricing4() {
        Person person = new Person(719, State.TX);
        Product product = new Product("7-1 ARM", 5.0, false, 60);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        Product newProduct = testRestTemplate.postForObject("http://localhost:" + port + "/product-price",
                personProductPair, Product.class);
        assertAll(
                () -> assertFalse(newProduct.isDisqualified()),
                () -> assertEquals(6.5, newProduct.getInterest_rate())
        );
    }

    @Test
    @DisplayName("POST /product-price - CreditScore : 719, State : TX, ProductName : 7-1 AIR, Term : 60")
    void shouldGetProductPricing5() {
        Person person = new Person(719, State.TX);
        Product product = new Product("7-1 AIR", 5.0, false, 60);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        Product newProduct = testRestTemplate.postForObject("http://localhost:" + port + "/product-price",
                personProductPair, Product.class);
        assertAll(
                () -> assertFalse(newProduct.isDisqualified()),
                () -> assertEquals(6.0, newProduct.getInterest_rate())
        );
    }

    @Test
    @DisplayName("POST /product-price - CreditScore : 719, State : TX, ProductName : 7-1 AIR, Term : 48")
    void shouldGetProductPricing6() {
        Person person = new Person(719, State.TX);
        Product product = new Product("7-1 AIR", 5.0, false, 48);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        Product newProduct = testRestTemplate.postForObject("http://localhost:" + port + "/product-price",
                personProductPair, Product.class);
        assertAll(
                () -> assertFalse(newProduct.isDisqualified()),
                () -> assertEquals(5.3, newProduct.getInterest_rate())
        );
    }

    @Test
    @DisplayName("POST /product-price - CreditScore : 299, State : FL, ProductName : 7-1 ARM, Term : 60")
    void shouldReturnBadRequest1() {
        Person person = new Person(299, State.FL);
        Product product = new Product("7-1 ARM", 5.0, false, 60);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        ResponseEntity<Product> responseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/product-price",
                personProductPair, Product.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
    }

    @Test
    @DisplayName("POST /product-price - CreditScore : 851, State : FL, ProductName : 7-1 ARM, Term : 60")
    void shouldReturnBadRequest2() {
        Person person = new Person(851, State.FL);
        Product product = new Product("7-1 ARM", 5.0, false, 60);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        ResponseEntity<Product> responseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/product-price",
                personProductPair, Product.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
    }

    @Test
    @DisplayName("POST /product-price - CreditScore : 720, State : FL, ProductName : NULL, Term : 60")
    void shouldReturnBadRequest3() {
        Person person = new Person(720, State.FL);
        Product product = new Product(null, 5.0, false, 60);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        ResponseEntity<Product> responseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/product-price",
                personProductPair, Product.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
    }

    @Test
    @DisplayName("POST /product-price - CreditScore : 720, State : NULL, ProductName : 7-1 ARM, Term : 6O")
    void shouldReturnBadRequest4() {
        Person person = new Person(720, null);
        Product product = new Product("7-1 ARM", 5.0, false, 60);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        ResponseEntity<Product> responseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/product-price",
                personProductPair, Product.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
    }

    @Test
    @DisplayName("POST /product-price - CreditScore : 720, State : FL, ProductName : LOWER BOUND - 3, Term : 60")
    void shouldReturnBadRequest5() {
        Person person = new Person(720, State.FL);
        Product product = new Product("ARM", 5.0, false, 60);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        ResponseEntity<Product> responseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/product-price",
                personProductPair, Product.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
    }

    @Test
    @DisplayName("POST /product-price - CreditScore : 720, State : FL, ProductName : UPPER BOUND - 20, Term : 60")
    void shouldReturnBadRequest6() {
        Person person = new Person(720, State.FL);
        Product product = new Product("it is not a valid state", 5.0, false, 60);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        ResponseEntity<Product> responseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/product-price",
                personProductPair, Product.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
    }

    @Test
    @DisplayName("POST /product-price - Person : NULL, State : FL, ProductName : 7-1 ARM ,Term: 60")
    void shouldReturnBadRequest7() {
        Product product = new Product("FL", 5.0, false, 60);
        PersonProductPair personProductPair = new PersonProductPair(null, product);
        ResponseEntity<Product> responseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/product-price",
                personProductPair, Product.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
    }

    @Test
    @DisplayName("POST /product-price - CreditScore : 720, State : FL, Product : NULL")
    void shouldReturnBadRequest8() {
        Person person = new Person(720, State.FL);
        PersonProductPair personProductPair = new PersonProductPair(person, null);
        ResponseEntity<Product> responseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/product-price",
                personProductPair, Product.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
    }

    @Test
    @DisplayName("POST /product-price - CreditScore : 720, State : FL, InterestRate : 5.5, Term : 60")
    void shouldReturnBadRequest9() {
        Person person = new Person(720, State.FL);
        Product product = new Product("FL", 5.5, false, 60);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        ResponseEntity<Product> responseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/product-price",
                personProductPair, Product.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
    }

    @Test
    @DisplayName("POST /product-price - CreditScore : 720, State : FL, InterestRate : 5.5, Term : 10 - Lower Bound")
    void shouldReturnBadRequest10() {
        Person person = new Person(720, State.FL);
        Product product = new Product("FL", 5.5, false, 10);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        ResponseEntity<Product> responseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/product-price",
                personProductPair, Product.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
    }

    @Test
    @DisplayName("POST /product-price - CreditScore : 720, State : FL, InterestRate : 5.5, Term : 121 - Upper Bound")
    void shouldReturnBadRequest11() {
        Person person = new Person(720, State.FL);
        Product product = new Product("FL", 5.5, false, 121);
        PersonProductPair personProductPair = new PersonProductPair(person, product);
        ResponseEntity<Product> responseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/product-price",
                personProductPair, Product.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
    }
}
