package com.techelevator.dao;
import com.techelevator.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

    public class InvoiceDaoTests extends BaseDaoTests {
        protected static final List<Service> INVOICE_SERVICES = new ArrayList<>();
        protected static final Service SERVICE_TUNE_UP = new Service(1, "Tune Up", 75.00, "Auto Repair Services");
        protected static final Service SERVICE_DIAGNOSTIC = new Service(2, "Diagnostic Test", 25.00, "Auto Repair Services");


        protected static final Vehicle WORK_ORDER_VEHICLE = new Vehicle(1, "make", "model", "year", "string");
        protected static final List<User> WORK_ORDER_USERS = new ArrayList<>();
        protected static final List<ServiceStatus> WORK_ORDER_SERVICES = new ArrayList<>();
        protected static final WorkOrder WORK_ORDER_1 = new WorkOrder(1, WORK_ORDER_VEHICLE, WORK_ORDER_USERS, WORK_ORDER_SERVICES);
        protected static final ServiceStatus SERVICE_1 = new ServiceStatus(SERVICE_TUNE_UP, new Status(5,"Completed"));
        protected static final User USER_1 = new User(1, "user1", "user1", "ROLE_USER");
        protected static final User USER_2 = new User(2, "user2", "user2", "ROLE_USER_EMPLOYEE");

        protected static final Invoice INVOICE_1 = new Invoice(1, USER_1, WORK_ORDER_1, INVOICE_SERVICES);

        private InvoiceDao dao;

    @Before
    public void setup() {
        INVOICE_SERVICES.add(SERVICE_TUNE_UP);
        INVOICE_SERVICES.add(SERVICE_DIAGNOSTIC);
        WORK_ORDER_USERS.add(USER_2);
        WORK_ORDER_SERVICES.add(SERVICE_1);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        UserDao userDao = new JdbcUserDao(jdbcTemplate);
        ServiceDao serviceDao = new JdbcServiceDao(jdbcTemplate);
        WorkOrderDao workOrderDao = new JdbcWorkOrderDao(jdbcTemplate, serviceDao);
        dao = new JdbcInvoiceDao(jdbcTemplate, userDao, workOrderDao);
    }

    @Test
    public void getInvoiceByInvoiceId_given_valid_id_returns_invoice() {
        Invoice invoice = dao.getInvoiceByInvoiceId(1);

        Assert.assertEquals(INVOICE_1.getInvoiceID(), invoice.getInvoiceID());
        Assert.assertEquals(INVOICE_1.getUser().getId(), invoice.getUser().getId());
        Assert.assertEquals(INVOICE_1.isPaid(), invoice.isPaid());
    }

}
