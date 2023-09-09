package com.techelevator.dao;

import com.techelevator.model.Invoice;
import com.techelevator.model.InvoiceDTO;

import java.util.List;

public interface InvoiceDao {

    Invoice getInvoiceByInvoiceId(int invoiceID);

    List<Invoice> getListOfInvoicesByUserId(int userId);

    List<Invoice> getAllInvoices();

    Invoice createInvoice(InvoiceDTO invoiceDTO);

    Invoice updateInvoice(Invoice invoice);




}
