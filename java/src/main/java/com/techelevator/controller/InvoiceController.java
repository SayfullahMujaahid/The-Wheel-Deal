package com.techelevator.controller;

import com.techelevator.dao.InvoiceDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.WorkOrderDao;
import com.techelevator.model.Invoice;
import com.techelevator.model.InvoiceDTO;
import com.techelevator.security.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class InvoiceController {

    @Autowired
    private InvoiceDao invoiceDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private WorkOrderDao workOrderDao;

    @RequestMapping(path = "/profile/{userId}/invoices", method = RequestMethod.GET)
    public List<Invoice> getInvoicesByUserId (Principal principal, @PathVariable int userId) {
        if (!userDao.getUserById(userId).getUsername().equalsIgnoreCase(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot get Invoices.");
        } else {
            return invoiceDao.getListOfInvoicesByUserId(userId);
        }
    }


    @RequestMapping(path = "/workorders/{workOrderId}/createinvoice", method = RequestMethod.POST)
    public Invoice createInvoice (Principal principal, @PathVariable int workOrderId,
                                  @Valid @RequestBody InvoiceDTO invoiceDTO) {
        if (userDao.getUserByUsername(principal.getName()).getRole().equalsIgnoreCase("customer")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Customer cannot create invoice.");
        } else {
            invoiceDTO.setWorkOrder(workOrderDao.getWorkOrderById(workOrderId));
            return invoiceDao.createInvoice(invoiceDTO);
        }
    }

    @RequestMapping(path = "/employeedashboard/invoices", method = RequestMethod.GET)
    public List<Invoice> getAllInvoices (Principal principal) {
        if (userDao.getUserByUsername(principal.getName()).getRole().equalsIgnoreCase("Customer")) {
            throw new DaoException("Cannot access all invoices");
        }

        return invoiceDao.getAllInvoices();
    }

    @RequestMapping(path = "employeedashboard/invoices/{invoiceId}", method = RequestMethod.PUT)
    public Invoice updateInvoice (Principal principal, @PathVariable int invoiceId, @Valid @RequestBody Invoice invoice) {
        if (userDao.getUserByUsername(principal.getName()).getRole().equalsIgnoreCase("customer")) {
            throw new DaoException("Cannot update invoice.");
        }
        invoice.setInvoiceID(invoiceId);
        return invoiceDao.updateInvoice(invoice);
    }
}
