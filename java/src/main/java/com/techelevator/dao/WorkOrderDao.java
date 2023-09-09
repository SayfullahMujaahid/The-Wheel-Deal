package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.stereotype.Component;

import java.util.List;

public interface WorkOrderDao {


    List<WorkOrder> getWorkOrders();

    List<WorkOrder> getWorkOrdersByUserId(int userId);

    WorkOrder getWorkOrderById(int workOrderId);

    WorkOrder createWorkOrder(WorkOrderDto workOrderDto);

    WorkOrder updateWorkOrder (WorkOrder workOrderToUpdate);






}
