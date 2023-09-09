package com.techelevator.model;

public class ServiceDto {

    private String serviceDescription;
    private String category;
    private double price;
    private double serviceTime;

    public ServiceDto(String serviceDescription, String category, double price) {
        this.serviceDescription = serviceDescription;
        this.category = category;
        this.price = price;
        this.serviceTime = 0.5;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
}
