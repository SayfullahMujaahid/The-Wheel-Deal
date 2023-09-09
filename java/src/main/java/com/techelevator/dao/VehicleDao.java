package com.techelevator.dao;

import com.techelevator.model.Vehicle;
import com.techelevator.model.VehicleDto;
import org.springframework.stereotype.Component;

import java.util.List;


public interface VehicleDao {

    List<Vehicle> getVehicles();
    List<Vehicle> getVehiclesByUserId(int userId);
    Vehicle getVehicleByVehicleId(int vehicleId);

    List<Vehicle> getVehiclesByMake(String make);

    List<Vehicle> getVehiclesByModel(String model);
    Vehicle createVehicle(VehicleDto vehicleDto, int userId);
    Vehicle updateVehicle(Vehicle vehicle);





}
