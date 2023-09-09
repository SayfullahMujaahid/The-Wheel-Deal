package com.techelevator.dao;

import com.techelevator.model.Service;
import com.techelevator.model.ServiceDto;
import com.techelevator.model.Vehicle;
import com.techelevator.security.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcServiceDao implements ServiceDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcServiceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Service> getServices() {
        List<Service> returnedServices = new ArrayList<>();
        String sql = "select service_id, service_description, " +
                "service_category, service_price, service_time " +
                "from service;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            while(rowSet.next()) {
                Service service = mapRowToService(rowSet);
                returnedServices.add(service);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database", e);
        }

        return returnedServices;
    }//completed

    @Override
    public List<String> getServiceCategories() {
        List<String> serviceCategories = new ArrayList<>();
        String sql = "select service_description from service;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            while (rowSet.next()) {
                String category = rowSet.getString("service_description");
                serviceCategories.add(category);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database.", e);
        }

        return serviceCategories;
    }//completed

    @Override
    public Service getServiceById(int serviceId) {
        Service returnedService = new Service();
        String sql = "select service_id, service_description, service_category, service_price, service_time\n" +
                "from service where service_id = ?;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, serviceId);
            if (rowSet.next()) {
                Service service = mapRowToService(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database.", e);
        }

        return returnedService;
    }//completed

    @Override
    public Service createService(ServiceDto serviceDto) {
        Service newService = new Service();

        String sql = "insert into service (service_description, service_category, " +
                "service_price, service_time) " +
                "values (?,?,?,?) returning service_id;";

        try {

            int newServiceId = jdbcTemplate.queryForObject(sql, int.class, serviceDto.getServiceDescription(),
                    serviceDto.getCategory(), serviceDto.getPrice(),
                    serviceDto.getServiceTime());

            newService =getServiceById(newServiceId);


        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Could not create new Service.",e );
        }

        return newService;
    }//completed

    @Override
    public Service updateService(Service service) {
        Service updatedService = new Service();

        String sql = "update service set service_description=?, service_category=?, " +
                "service_price=?, service_time=? " +
                "where service_id=?;";

        try {

            int updatedRows = jdbcTemplate.update(sql, service.getServiceDescription(), service.getCategory(),
                    service.getPrice(), service.getServiceTime(), service.getServiceID());

            if (updatedRows == 0) {
                throw new DaoException("Could not update Service.");
            } else {
                updatedService = getServiceById(service.getServiceID());
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Could not update Service.", e);
        }

        return updatedService;
    }//completed




    public Service mapRowToService (SqlRowSet rowSet) {
        Service service = new Service();
        service.setServiceID(rowSet.getInt("service_id"));
        service.setServiceDescription(rowSet.getString("service_description"));
        service.setCategory(rowSet.getString("service_category"));
        service.setPrice(rowSet.getDouble("service_price"));
        service.setServiceTime(rowSet.getDouble("service_time"));
        return service;
    }
}
