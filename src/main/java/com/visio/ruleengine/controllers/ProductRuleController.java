package com.visio.ruleengine.controllers;
import com.visio.ruleengine.RuleInit;
import com.visio.ruleengine.models.PersonProductPair;
import com.visio.ruleengine.models.Product;
import com.visio.ruleengine.services.RuleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;

@RestController
@AllArgsConstructor
public class ProductRuleController {
    private static final Logger LOG = LoggerFactory.getLogger(ProductRuleController.class);

    private final RuleInit ruleInit;

    private final RuleService ruleService;



    /**
     * Returns the productPrice and eligibility
     * @param personProductPair The demographic data of the person, initial interest rate and requested product
     * @param bindingResult catches any error happens during binding
     * @return the productPrice and eligibility
     */
    @PostMapping("/productPrice")
    ResponseEntity<Product> submit(@Valid @RequestBody final PersonProductPair personProductPair, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            LOG.warn("Validation failed: {}", bindingResult);
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        Product newProduct = ruleService.applyRules(personProductPair, ruleInit.getRules());
        return ResponseEntity
                    .ok()
                    .body(newProduct);
    }
}
