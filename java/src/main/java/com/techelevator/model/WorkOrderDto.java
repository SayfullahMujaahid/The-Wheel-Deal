package com.techelevator.model;

import java.util.List;

public class WorkOrderDto {


    private User customer;
    private Vehicle vehicle;
    private List<Service> services;

    public WorkOrderDto() {

    }
    public WorkOrderDto(User customer, Vehicle vehicle, List<Service> services) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.services = services;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}

