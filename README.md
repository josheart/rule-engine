# Rule Engine

Rule Engine dynamically generates product pricing from a set of rules defined by the Visio Finance Team.

## Getting Started

* Clone or download the repo

### Prerequisites

* Java 1.8
* Maven 3.6
* Lombok IDE Plugin

### Installing

* Open the project with your favorite IDE supports Java Development Env
* `mvn clean install` to install the dependencies, test and build the project.
* Start the application running the main method from `RuleEngineApplication.java`
* Open a REST client like `Postman`
* `POST` the below sample payload to the `http://localhost:8080/product-price`
```
{
    "person": {
    "credit_score": 720,
    "state": "FL"
    },
    "product": {
    "name": "7-1 ARM",
    "interest_rate": 5.0,
    "term" : 60
    }
}
```
And you will get below sample response

```
{
    "name": "7-1 ARM",
    "interest_rate": 5.7,
    "disqualified": true,
    "term": 60
}
```

## Running the tests

* `mvn test` executes all the tests. 
* Also tests can be executed from the IDE by package, by class or by method. This execution provides fancy test results.

### Break down into end to end tests

* 17 Integration, 19 Unit total 36 test cases are written.

* During development `Postman` used to perform functional tests.

## Built With

* [Java8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - The programming language
* [Spring Boot](https://spring.io/projects/spring-boot) - The framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Mockito](https://site.mockito.org/) - Used to perform Unit Tests
* [JUnit5](https://junit.org/junit5/) - Used to perform Unit and Integration Tests
* [IntelliJ](https://www.jetbrains.com/idea/) - IDE
* [Postman](https://www.getpostman.com/) - Used to perform functional tests


## Authors

* **Cesur Ercan** - [josheart](https://github.com/josheart)
