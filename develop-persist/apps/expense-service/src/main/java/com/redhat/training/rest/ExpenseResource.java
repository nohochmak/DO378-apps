package com.redhat.training.rest;

import java.util.List;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.DefaultValue;

import com.redhat.training.model.Expense;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

@Path("/expenses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExpenseResource {

    @GET
    //  1: Implement with a call to "listAll()" of Expense entity.
    // TODO 2: Add pagination and sort by "amount" and "associateId".
    public List<Expense> list() {
        return Expense.listAll();
    }

    @POST
    // Make the method transactional
    @Transactional
    public Expense create(final Expense expense) {
        Expense newExpense = Expense.of(expense.name, expense.paymentMethod,
                expense.amount.toString(), expense.associateId);
        // Use the "persist()" method of the entity.
        newExpense.persist();

        return newExpense;
    }

    @DELETE
    @Path("{id}")
    // Make the method transactional
    @Transactional
    public void delete(@PathParam("id") final Integer id) {
        // Use the "delete()" method of the entity and list the expenses
        long numExpensesDeleted = Expense.delete("id", id);

        if (numExpensesDeleted == 0) { 
            throw new WebApplicationException(Response.Status.NOT_FOUND); 
        }
    }

    @PUT
    // Make the method transactional
    @Transactional
    public void update(final Expense expense) {
        // Use the "update()" method of the entity.
        try {
            Expense.update(expense); 
        } catch (RuntimeException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND); 
        }
    }
}
