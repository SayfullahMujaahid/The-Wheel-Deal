package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarModelDTO {
    @JsonProperty("name")
    private String model;

    public CarModelDTO() {

    }
    public CarModelDTO(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}