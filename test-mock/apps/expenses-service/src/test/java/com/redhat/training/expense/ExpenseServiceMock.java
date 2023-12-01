package com.redhat.training.expense;

import java.util.UUID;

import io.quarkus.test.Mock;
import jakarta.enterprise.context.ApplicationScoped;

@Mock
@ApplicationScoped
public class ExpenseServiceMock extends ExpenseService {
    @Override
    public boolean exists(UUID uuid) {
        //return Expense.find("uuid", uuid).count() == 1;
        //return true;
        return !uuid.equals( UUID.fromString(CrudTest.NON_EXISTING_UUID));
    }
}

