package com.visio.ruleengine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.PersonProductPair;
import com.visio.ruleengine.models.Product;
import com.visio.ruleengine.rules.State;
import com.visio.ruleengine.services.RuleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    /**
     * A mock version of the RuleService for use in our tests
     */
    @MockBean
    private RuleService service;

    /**
     * The controller that we want to test
     */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /product - Success")
    void shouldGetProductPrice() throws Exception {
        Person postPerson = new Person(720, State.FL);
        Product postProduct = new Product("7-1 ARM", 5.0, false);
        PersonProductPair postPersonProductPair = new PersonProductPair(postPerson, postProduct);
        Product mockProduct = new Product("7-1 ARM", 5.2, true);
        doReturn(mockProduct).when(service).applyRules(any(), any());

        mockMvc.perform(post("/productPrice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postPersonProductPair)))

                //Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                //Validate the returned fields
                .andExpect(jsonPath("$.name").value("7-1 ARM"))
                .andExpect(jsonPath("$.interest_rate").value(5.2))
                .andExpect(jsonPath("$.disqualified").value(true));

    }

    @Test
    @DisplayName("POST /product - Bad Request, Invalid InterestRate")
    void getBadRequestWithInvalidInterest() throws Exception {
        Person postPerson = new Person(850, State.FL);
        Product postProduct = new Product("7-1 ARM", 4.0, false);
        PersonProductPair postPersonProductPair = new PersonProductPair(postPerson, postProduct);
        Product mockProduct = new Product("7-1 ARM", 5.2, true);
        doReturn(mockProduct).when(service).applyRules(any(), any());

        mockMvc.perform(post("/productPrice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postPersonProductPair)))

                //Validate the response code
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /product - Bad Request, Invalid Credit Score - UpperBound")
    void getBadRequestWithInvalidCreditScoreUpper() throws Exception {
        Person postPerson = new Person(851, State.FL);
        Product postProduct = new Product("7-1 ARM", 5.0, false);
        PersonProductPair postPersonProductPair = new PersonProductPair(postPerson, postProduct);
        Product mockProduct = new Product("7-1 ARM", 5.2, true);
        doReturn(mockProduct).when(service).applyRules(any(), any());

        mockMvc.perform(post("/productPrice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postPersonProductPair)))

                //Validate the response code
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /product - Bad Request, Invalid Credit Score - LowerBound")
    void getBadRequestWithInvalidCreditScoreLower() throws Exception {
        Person postPerson = new Person(299, State.FL);
        Product postProduct = new Product("7-1 ARM", 5.0, false);
        PersonProductPair postPersonProductPair = new PersonProductPair(postPerson, postProduct);
        Product mockProduct = new Product("7-1 ARM", 5.2, true);
        doReturn(mockProduct).when(service).applyRules(any(), any());

        mockMvc.perform(post("/productPrice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postPersonProductPair)))

                //Validate the response code
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /product - Bad Request, Without ProductName")
    void getBadRequestWithoutProductName() throws Exception {
        Person postPerson = new Person(720, State.FL);
        Product postProduct = new Product("", 5.0, false);
        PersonProductPair postPersonProductPair = new PersonProductPair(postPerson, postProduct);
        Product mockProduct = new Product("7-1 ARM", 5.2, true);
        doReturn(mockProduct).when(service).applyRules(any(), any());

        mockMvc.perform(post("/productPrice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postPersonProductPair)))

                //Validate the response code
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /product - Bad Request, Invalid ProductName - LowerBound Length")
    void getBadRequestWithInvalidProductNameLowerB() throws Exception {
        Person postPerson = new Person(720, State.FL);
        Product postProduct = new Product("ARM", 5.0, false);
        PersonProductPair postPersonProductPair = new PersonProductPair(postPerson, postProduct);
        Product mockProduct = new Product("7-1 ARM", 5.2, true);
        doReturn(mockProduct).when(service).applyRules(any(), any());

        mockMvc.perform(post("/productPrice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postPersonProductPair)))

                //Validate the response code
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /product - Bad Request, Invalid ProductName - UpperBound Length")
    void getBadRequestWithInvalidProductNameUpperB() throws Exception {
        Person postPerson = new Person(720, State.FL);
        Product postProduct = new Product("product name is too long", 5.0, false);
        PersonProductPair postPersonProductPair = new PersonProductPair(postPerson, postProduct);
        Product mockProduct = new Product("7-1 ARM", 5.2, true);
        doReturn(mockProduct).when(service).applyRules(any(), any());

        mockMvc.perform(post("/productPrice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postPersonProductPair)))

                //Validate the response code
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("POST /product - Bad Request, Without PersonObject")
    void getBadRequestWithoutPersonObject() throws Exception {
        Product postProduct = new Product("", 5.0, false);
        PersonProductPair postPersonProductPair = new PersonProductPair(null, postProduct);
        Product mockProduct = new Product("7-1 ARM", 5.2, true);
        doReturn(mockProduct).when(service).applyRules(any(), any());

        mockMvc.perform(post("/productPrice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postPersonProductPair)))

                //Validate the response code and content type
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /product - Bad Request, Without ProductObject")
    void getBadRequestWithoutProductObject() throws Exception {
        Person postPerson = new Person(720, State.FL);
        PersonProductPair postPersonProductPair = new PersonProductPair(postPerson, null);
        Product mockProduct = new Product("7-1 ARM", 5.0, true);
        doReturn(mockProduct).when(service).applyRules(any(), any());

        mockMvc.perform(post("/productPrice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postPersonProductPair)))

                //Validate the response code and content type
                .andExpect(status().isBadRequest());
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
