package com.redhat.training.expense;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;


@QuarkusTest
public class SpyTest {
    @InjectSpy
    ExpenseService expenseService;

    @Test 
    public void testExample() {

        given() 
        .when()
            .get("/expenses") 
        .then()
            .statusCode(200)
            .body("$.size()", greaterThanOrEqualTo(0));

        Mockito.verify( expenseService, Mockito.times(1)).list();

}       
}
