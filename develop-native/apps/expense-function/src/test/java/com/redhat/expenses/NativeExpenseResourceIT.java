package com.redhat.expenses;

import io.quarkus.test.junit.QuarkusIntegrationTest;
// NativeImageTest was replaced by QIT for version Quarkus 3
//import io.quarkus.test.junit.NativeImageTest;

@QuarkusIntegrationTest
public class NativeExpenseResourceIT extends ExpenseResourceTest {

    // Execute the same tests but in native mode.
}