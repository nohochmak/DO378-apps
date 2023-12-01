package com.redhat.training.expense;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.InjectMock;
import io.restassured.http.ContentType;

import static org.mockito.ArgumentMatchers.anyDouble;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class ServiceMockTest {
    @InjectMock
    ExpenseService mockExpenseService;

    @Test
    public void testExpense() {
        Mockito
            .when( mockExpenseService.meetsMinimumAmount( Mockito.anyDouble()  ) ) 
            .thenReturn( false );

            given() 
                .body( CrudTest.generateExpenseJson("", "Expense 1", "CASH", 99999 ))
                .contentType( ContentType.JSON )
            .when()
                .post("/expenses")
            .then()
                .statusCode(400);
             
    }
 
}
