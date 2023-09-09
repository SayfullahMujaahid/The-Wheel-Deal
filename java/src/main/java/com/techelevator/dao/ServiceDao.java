package com.techelevator.dao;

import com.techelevator.model.Service;
import com.techelevator.model.ServiceDto;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public interface ServiceDao {

    List<Service> getServices();

    List<String> getServiceCategories();

    Service getServiceById(int serviceId);
    Service createService(ServiceDto serviceDto);

    Service updateService(Service service);
    Service mapRowToService (SqlRowSet rowSet);

}
