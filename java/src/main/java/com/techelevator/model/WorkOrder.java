package com.techelevator.model;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotNull;
import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class WorkOrder {


    private int workOrderId;
    private Vehicle vehicle;
    @NotNull
    private List<User> users;
    private double timeAdjustment; // set default to 0 in constructor
    private boolean isApproved; //set default to false in constructor
    private List<ServiceStatus> serviceStatuses;
    private boolean isCompleted;



    public WorkOrder() {
    }

    public WorkOrder(int workOrderId, Vehicle vehicle, List<User> users,
                     List<ServiceStatus> serviceStatuses) {
        this.workOrderId = workOrderId;
        this.vehicle = vehicle;
        this.users = users;
        this.timeAdjustment = 0.00;
        this.isApproved = false;
        this.serviceStatuses = serviceStatuses;
        this.isCompleted = false;
    }

    public WorkOrder(int workOrderId, List<User> users, List<ServiceStatus> serviceStatuses) {
        this.workOrderId = workOrderId;
        this.users = users;
        this.timeAdjustment = 0.00;
        this.isApproved = false;
        this.serviceStatuses = serviceStatuses;
        this.isCompleted = false;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(int workOrderId) {
        this.workOrderId = workOrderId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public double getTimeAdjustment() {
        return timeAdjustment;
    }

    public void setTimeAdjustment(double timeAdjustment) {
        this.timeAdjustment = timeAdjustment;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public List<ServiceStatus> getServiceStatuses() {
        return serviceStatuses;
    }

    public void setServiceStatuses(List<ServiceStatus> serviceStatuses) {
        this.serviceStatuses = serviceStatuses;
    }
}