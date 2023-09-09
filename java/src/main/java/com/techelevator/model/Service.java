package com.techelevator.model;

import javax.validation.constraints.NotNull;
import java.util.List;

public class Service {

    private int serviceID;
    @NotNull
    private String serviceDescription;
    @NotNull
    private String category;
    private double price;
    private double serviceTime;


    public Service() {

    }

    public Service(int serviceID, String serviceDescription, double price, String category) {
        this.serviceID = serviceID;
        this.serviceDescription = serviceDescription;
        this.price = price;
        this.serviceTime = 0.5;
        this.category = category;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(double serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}