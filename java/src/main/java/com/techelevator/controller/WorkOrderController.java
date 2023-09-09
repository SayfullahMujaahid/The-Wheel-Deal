package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.dao.WorkOrderDao;
import com.techelevator.model.WorkOrder;
import com.techelevator.model.WorkOrderDto;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class WorkOrderController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private WorkOrderDao workOrderDao;


    @RequestMapping(path = "/profile/{userId}/currentservices", method = RequestMethod.GET)
    public List<WorkOrder> getWorkOrderByUserId(Principal principal, @PathVariable int userId) {
        if (userDao.getUserByUsername(principal.getName()).getId() != userId) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot get work orders.");
        } else {
            List<WorkOrder> onlyOpenWorkOrders = new ArrayList<>();
            List<WorkOrder> returnedWorkOrders = workOrderDao.getWorkOrdersByUserId(userId);

            for (WorkOrder workOrder : returnedWorkOrders) {
                if (!workOrder.isApproved()) {
                    onlyOpenWorkOrders.add(workOrder);
                }
            }

            return onlyOpenWorkOrders;
        }
    }

    @RequestMapping(path = "/employeedashboard/workorders", method = RequestMethod.GET)
    public List<WorkOrder> getAllWorkOrders(Principal principal, @RequestParam(defaultValue = "-1") int userId,
                                            @RequestParam(required = false) Boolean isCompleted) {
        if (userDao.getUserByUsername(principal.getName()).getRole().equalsIgnoreCase("customer")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot access all work orders.");
        } else {
            if (userId < 0 && isCompleted == null) {
                return workOrderDao.getWorkOrders();
            }
            if (userId < 0) {
                List<WorkOrder> filteredResults = new ArrayList<>();
                List<WorkOrder> returnedResults = workOrderDao.getWorkOrders();
                if (isCompleted) {
                    for (WorkOrder workOrder : returnedResults) {
                        if (workOrder.isCompleted()) {
                            filteredResults.add(workOrder);
                        }
                    }
                    return filteredResults;

                } else {
                    for (WorkOrder workOrder : returnedResults) {
                        if (!workOrder.isCompleted()) {
                            filteredResults.add(workOrder);
                        }
                    }
                }
            }
            if (userId > 0) {
                if (isCompleted == null) {
                    return workOrderDao.getWorkOrdersByUserId(userId);
                } else if (isCompleted) {
                    List<WorkOrder> filteredResults = new ArrayList<>();
                    List<WorkOrder> returnedResults = workOrderDao.getWorkOrdersByUserId(userId);
                    for (WorkOrder workOrder : returnedResults) {
                        if (workOrder.isCompleted()) {
                            filteredResults.add(workOrder);
                        }
                    }
                    return filteredResults;
                } else {
                    List<WorkOrder> filteredResults = new ArrayList<>();
                    List<WorkOrder> returnedResults = workOrderDao.getWorkOrdersByUserId(userId);
                    for (WorkOrder workOrder : returnedResults) {
                        if (!workOrder.isCompleted()) {
                            filteredResults.add(workOrder);
                        }
                    }
                    return filteredResults;

                }
            }
        }
        return workOrderDao.getWorkOrders();
    }

    @RequestMapping(path = "/profile/{userId}/currentservices", method = RequestMethod.POST)
    public WorkOrder createWorkOrder (Principal principal, @PathVariable int userId,
                                      @Valid @RequestBody WorkOrderDto workOrderDto) {
        if (!userDao.getUserById(userId).getUsername().equalsIgnoreCase(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot create Appointment");
        }
        workOrderDto.setCustomer(userDao.getUserById(userId));
        return workOrderDao.createWorkOrder(workOrderDto);
    }

    @RequestMapping(path = "/employeedashboard/workorders/{workOrderId}", method = RequestMethod.PUT)
    public WorkOrder updateWorkOrder (Principal principal, @PathVariable int workOrderId,
                                      @RequestBody @Valid WorkOrder workOrder) {
        if (userDao.getUserByUsername(principal.getName()).getRole().equalsIgnoreCase("customer")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot update Work Order");
        } else {
            workOrder.setWorkOrderId(workOrderId);
            return workOrderDao.updateWorkOrder(workOrder);
        }


    }




}
