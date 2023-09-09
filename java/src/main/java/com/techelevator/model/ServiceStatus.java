package com.techelevator.model;

public class ServiceStatus {

    private Service service;
    private Status status;

    public ServiceStatus() {

    }

    public ServiceStatus(Service service, Status status) {
        this.service = service;
        this.status = status;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
