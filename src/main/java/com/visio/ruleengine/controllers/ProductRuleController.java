package com.visio.ruleengine.controllers;
import com.visio.ruleengine.models.Data;
import lombok.AllArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/productPrice")
public class ProductRuleController {
    private static final Logger LOG = LoggerFactory.getLogger(ProductRuleController.class);

    private final KieSession session;

    @PostMapping
    public ResponseEntity<Data> submit(@Valid @RequestBody final Data data, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            LOG.warn("Validation failed: {}", bindingResult);
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        session.insert(data);
        session.fireAllRules();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
