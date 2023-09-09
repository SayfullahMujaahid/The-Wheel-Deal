package com.techelevator.model;
;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Status {

    private int statusId;
    @NotNull
    private String description;

    public Status() {

    }
    public Status(int statusId, String description) {
        this.statusId = statusId;
        this.description = description;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
