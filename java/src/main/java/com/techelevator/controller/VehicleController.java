package com.techelevator.controller;

import com.techelevator.dao.JdbcServiceDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.VehicleDao;
import com.techelevator.model.Vehicle;
import com.techelevator.model.VehicleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class VehicleController {

    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private UserDao userDao;

    @RequestMapping(path = "/profile/{userId}/vehicles", method = RequestMethod.GET)
    public List<Vehicle> getVehiclesByUserId (Principal principal, @PathVariable int userId) {
        if (!userDao.getUserById(userId).getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot access another User's Vehicles.");
        } else {
            return vehicleDao.getVehiclesByUserId(userId);
        }
    }

    @RequestMapping (value = "/profile/{userId}/vehicles", method = RequestMethod.POST)
    public Vehicle createVehicle (Principal principal, @RequestBody VehicleDto vehicleDto, @PathVariable int userId) {
        if (!userDao.getUserById(userId).getUsername().equalsIgnoreCase(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot add vehicle");
        } else {
            return vehicleDao.createVehicle(vehicleDto, userId);
        }
    }





}
