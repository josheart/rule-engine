package com.visio.ruleengine.controllers;
import com.visio.ruleengine.models.PersonProductPair;
import com.visio.ruleengine.models.Product;
import com.visio.ruleengine.services.ProductRuleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/productPrice")
public class ProductRuleController {
    private static final Logger LOG = LoggerFactory.getLogger(ProductRuleController.class);

    private final ProductRuleService productRuleService;

    @PostMapping
    ResponseEntity<Product> submit(@RequestBody final PersonProductPair personProductPair){
        return new ResponseEntity<>(productRuleService.getProductPricing(personProductPair), HttpStatus.OK);

    }
}
