package com.bspicinini.controller;

import java.math.BigDecimal;

import io.quarkus.test.TestTransaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bspicinini.repository.ContractRepository;
import com.bspicinini.repository.CustomerRepository;
import com.bspicinini.repository.entity.Contract;
import com.bspicinini.repository.entity.ContractStatusEnum;
import com.bspicinini.repository.entity.Customer;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class ContractControllerTest {

    @Inject
    ContractRepository contractRepository;

    @Inject
    CustomerRepository customerRepository;

    @PersistenceContext
    EntityManager entityManager;

    @BeforeEach
    @Transactional
    void setup() {
        var customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("johndoe@example.com");
        customerRepository.persist(customer);

        var contract = new Contract();
        contract.setStatus(ContractStatusEnum.ACTIVE);
        contract.setAmount(BigDecimal.valueOf(1000.0));
        contract.setInstallments(12);
        contract.setCustomer(customer);
        contractRepository.persist(contract);
    }

    @AfterEach
    @Transactional
    void tearDown() {
        contractRepository.deleteAll();
        customerRepository.deleteAll();
        resetSequences();
    }

    @Transactional
    void resetSequences() {
        entityManager.createNativeQuery("ALTER SEQUENCE contract_sequence RESTART WITH 1").executeUpdate();
        entityManager.createNativeQuery("ALTER SEQUENCE customer_sequence RESTART WITH 1").executeUpdate();
    }

    @Test
    @TestTransaction
    void givenAValidRequest_whenGetContractById_thenReturnAContract() {
        RestAssured.given().contentType(ContentType.JSON).pathParam("id", 1)
                .when().get("/contracts/{id}")
                .then()
                .statusCode(200)
                .body("id", is(1))
                .body("amount", notNullValue());
    }

    @Test
    @TestTransaction 
    void givenAValidRequest_whenCreateContract_thenReturnCreatedContract() {
        String contractJson = """
                    {              
                        "amount": 2000.0,
                        "installments": 24,
                        "customerId": 1
                    }
                """;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(contractJson)
                .when().post("/contracts")
                .then()
                .statusCode(201)
                .body("amount", is(2000.0f))
                .body("installments", is(24))
                .body("customerId", is(1));
    }
}