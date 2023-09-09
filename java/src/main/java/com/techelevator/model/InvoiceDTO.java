package com.techelevator.model;

import javax.validation.constraints.AssertTrue;

public class InvoiceDTO {


    private User user;
    private WorkOrder workOrder;


    public InvoiceDTO(User user, WorkOrder workOrder) {
        this.user = user;
        this.workOrder = workOrder;
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
}
