package com.techelevator.dao;

import com.techelevator.model.*;
import com.techelevator.security.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcInvoiceDao implements InvoiceDao {

    private JdbcTemplate jdbcTemplate;
    private UserDao jdbcUserDao;
    private WorkOrderDao jdbcWorkOrderDao;

    public JdbcInvoiceDao(JdbcTemplate jdbcTemplate, UserDao userDao, WorkOrderDao workOrderDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcUserDao = userDao;
        this.jdbcWorkOrderDao = workOrderDao;
    }


    @Override
    public Invoice getInvoiceByInvoiceId(int invoiceID) {
        Invoice returnedInvoice = new Invoice();
        String sql = "select invoice.invoice_id, invoice.user_id, invoice.work_order_id, is_paid \n" +
                "from invoice\n" +
                "where invoice.invoice_id = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, invoiceID);

        try {
            if (rowSet.next()) {
                returnedInvoice.setInvoiceID(rowSet.getInt("invoice_id"));
                returnedInvoice.setUser(jdbcUserDao.getUserById(rowSet.getInt("user_id")));
                returnedInvoice.setWorkOrder(jdbcWorkOrderDao.getWorkOrderById(rowSet.getInt("work_order_id")));
                returnedInvoice.setPaid(rowSet.getBoolean("is_paid"));
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database", e);
        }

        return returnedInvoice;
    }//COMPLETED

    @Override
    public List<Invoice> getListOfInvoicesByUserId(int userId) {
        List<Invoice> returnedInvoices = new ArrayList<>();
        String sql = "select invoice.invoice_id, invoice.user_id, invoice.work_order_id, is_paid\n" +
                "from invoice\n" +
                "join user_invoice on invoice.user_id = user_invoice.user_id\n" +
                "where user_invoice.user_id = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);

        try {

            while (rowSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceID(rowSet.getInt("invoice_id"));
                User user = jdbcUserDao.getUserById(rowSet.getInt("user_id"));
                invoice.setUser(user);
                WorkOrder workOrder = jdbcWorkOrderDao.getWorkOrderById(rowSet.getInt("work_order_id"));
                invoice.setWorkOrder(workOrder);
                invoice.setPaid(rowSet.getBoolean("is_paid"));
                returnedInvoices.add(invoice);
            }


        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database.", e);
        }

        return returnedInvoices;
    }//COMPLETED

    @Override
    public List<Invoice> getAllInvoices() {
        List<Invoice> returnedInvoices = new ArrayList<>();
        String sql = "select invoice.invoice_id, invoice.user_id, invoice.work_order_id, is_paid, username, first_name, last_name, role, work_order.vehicle_id, time_adjustment, is_approved, is_completed, make, model, year, color\n" +
                "from invoice\n" +
                "join user_invoice on invoice.invoice_id = user_invoice.invoice_id\n" +
                "join users on user_invoice.user_id = users.user_id\n" +
//                "join users_work_order on users.user_id = users_work_order.user_id\n" +
                "join work_order on invoice.work_order_id = work_order.work_order_id\n" +
                "join vehicle on work_order.vehicle_id = vehicle.vehicle_id;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        try {

            while (rowSet.next()) {
                Invoice invoice = new Invoice();
                User user = new User();
                WorkOrder workOrder = new WorkOrder();
                invoice.setInvoiceID(rowSet.getInt("invoice_id"));
                user = JdbcWorkOrderDao.mapRowToUser(rowSet);
                invoice.setUser(user);
                workOrder = jdbcWorkOrderDao.getWorkOrderById(rowSet.getInt("work_order_id")); //JdbcWorkOrderDao.mapRowToWorkOrder(rowSet);
                invoice.setWorkOrder(workOrder);
                returnedInvoices.add(invoice);

            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database", e);
        }

        return returnedInvoices;
    }//COMPLETED

    @Override
    public Invoice createInvoice(InvoiceDTO invoiceDTO) {
        Invoice createdInvoice = new Invoice();

        String sql = "insert into invoice (user_id, work_order_id) " +
                "values (?,?) returning invoice_id;";
        String sqlLinkInvoiceToUser = "insert into user_invoice (user_id, invoice_id) " +
                "values (?,?);";

        try {
            int createdInvoiceId = jdbcTemplate.queryForObject(sql, int.class, invoiceDTO.getUser().getId(),
                    invoiceDTO.getWorkOrder().getWorkOrderId());

            jdbcTemplate.update(sqlLinkInvoiceToUser, invoiceDTO.getUser().getId(),
                    createdInvoiceId);

            createdInvoice = getInvoiceByInvoiceId(createdInvoiceId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Could not create new Invoice.", e);
        }

        return createdInvoice;
    }//COMPLETED

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Invoice updatedInvoice = new Invoice();

        String sql = "update invoice set user_id=?, work_order_id=?, is_paid=? " +
                "where invoice_id=?;";

        try {

            int updatedRows = jdbcTemplate.update(sql, invoice.getUser().getId(),
                    invoice.getWorkOrder().getWorkOrderId(), invoice.isPaid(), invoice.getInvoiceID());

            if (updatedRows == 0) {
                throw new DaoException("Could not update Invoice.");
            } else {
                updatedInvoice = getInvoiceByInvoiceId(invoice.getInvoiceID());
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database.", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Could not update Invoice.", e);
        }

        return updatedInvoice;
    }//COMPLETED


}
