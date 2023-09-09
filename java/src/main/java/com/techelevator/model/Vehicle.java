package com.techelevator.model;

import javax.validation.constraints.NotNull;

public class Vehicle {
    @NotNull

    private int vehicleId;
    private String make;
    private String model;
    private String year;
    private String color;
    private String plateNumber;
    private int mileage;

    public Vehicle() {}

    public Vehicle(int vehicleId, String make, String model, String year, String color, String plateNumber, int mileage) {
        this.vehicleId = vehicleId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.plateNumber = plateNumber;
        this.mileage = mileage;
    }

    public Vehicle(int vehicleId, String make, String model, String year, String color) {
        this.vehicleId = vehicleId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
