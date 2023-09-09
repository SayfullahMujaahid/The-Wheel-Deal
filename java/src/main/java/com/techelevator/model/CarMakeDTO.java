package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarMakeDTO {

    @JsonProperty("name")
    private String make;

    @JsonProperty("id")
    private int makeId;

    public CarMakeDTO() {

    }

    public CarMakeDTO(String make, int makeId) {
        this.make = make;
        this.makeId = makeId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getMakeId() {
        return makeId;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }
}