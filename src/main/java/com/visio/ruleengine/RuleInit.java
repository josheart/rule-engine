package com.visio.ruleengine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visio.ruleengine.models.Person;
import com.visio.ruleengine.models.Product;
import com.visio.ruleengine.rules.ProductRule;
import com.visio.ruleengine.rules.Rule;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Component
public class RuleInit {
    private static final Logger LOG = LoggerFactory.getLogger(RuleInit.class);

    private List<Rule<Product, Person>> rules;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${rule.path}")
    private String rulePath;

    /**
     * Loads rules one time to memory during server init
     */
    @PostConstruct
    private void loadRules() {
        try {
            Path path = Paths.get(rulePath);
            String content = Files.lines(path).collect(Collectors.joining("\n"));
            LOG.info(content);
            Rule<Product, Person>[] rules = objectMapper.readValue(content, ProductRule[].class);
            this.rules = Arrays.asList(rules);
            LOG.info("Product pricing rules are loaded");
        } catch (IOException e) {
            LOG.error("Error loading product prices", e);
        }
    }
}
