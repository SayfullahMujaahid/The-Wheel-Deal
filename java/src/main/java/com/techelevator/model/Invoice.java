package com.techelevator.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Invoice {

    private int invoiceID;
    @NotNull
    private User user;
    @NotNull
    private WorkOrder workOrder;

    private boolean isPaid;



    @AssertTrue
    private boolean isUserCustomer() {
        return user.getRole().equalsIgnoreCase("customer");
    }




    public Invoice() {

    }

    public Invoice(int invoiceID, User user, WorkOrder workOrder, List<Service> services) {
        this.invoiceID = invoiceID;
        this.user = user;
        this.workOrder = workOrder;

        this.isPaid = false;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WorkOrder getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }


    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}

