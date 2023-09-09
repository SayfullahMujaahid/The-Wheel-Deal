package com.techelevator.controller;

import com.techelevator.dao.JdbcServiceDao;
import com.techelevator.dao.ServiceDao;
import com.techelevator.model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
public class ServiceController {

    @Autowired
    private ServiceDao serviceDao;

    @RequestMapping(path = "/services", method = RequestMethod.GET)
    public List<Service> getServices() {
        return serviceDao.getServices();
    }




}
