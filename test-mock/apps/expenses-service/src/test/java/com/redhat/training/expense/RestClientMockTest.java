package com.redhat.training.expense;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.InjectMock;
import io.restassured.http.ContentType;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;

import java.util.UUID;

@QuarkusTest
public class RestClientMockTest {
    @InjectMock 
    @RestClient 
    FraudScoreService scoringService;

    @Test 
    public void highFraudScoreReturns400() { 
        Mockito
            .when( scoringService.getByAmount( Mockito.anyDouble() )) 
            .thenReturn(new FraudScore( 500 ));

        given() 
            .body( CrudTest.generateExpenseJson("", "Expense 1", "CASH", 50000 ))
            .contentType( ContentType.JSON )
        .when()
            .post("/expenses/score")
        .then()
            .statusCode(400);
    }

    @Test 
    public void lowFraudScoreReturns200() { 
        Mockito
            .when( scoringService.getByAmount( Mockito.anyDouble() )) 
            .thenReturn(new FraudScore( 50 ));

        given() 
            .body( CrudTest.generateExpenseJson("", "Expense 1", "CASH", 50000 ))
            .contentType( ContentType.JSON )
        .when()
            .post("/expenses/score")
        .then()
            .statusCode(200);
    }
}
