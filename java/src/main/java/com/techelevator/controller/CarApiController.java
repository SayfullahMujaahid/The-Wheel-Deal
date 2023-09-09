package com.techelevator.controller;

import com.techelevator.model.CarMakeDTO;
import com.techelevator.model.CarModelDTO;
import com.techelevator.services.CarApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@CrossOrigin
public class CarApiController {
    private final CarApiService carApiService;

    public CarApiController(CarApiService carApiService) {
        this.carApiService = carApiService;
    }

    @GetMapping("/makes")//need path
    public List<CarMakeDTO> getMakes() {
        return carApiService.getMakes();
    }

    @GetMapping("/models") //need path
    public List<CarModelDTO> getModelsByMake(@RequestParam String make) {
        return carApiService.getModelsByMake(make);
    }
}