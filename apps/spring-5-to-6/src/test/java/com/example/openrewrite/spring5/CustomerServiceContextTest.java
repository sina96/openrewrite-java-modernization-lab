package com.example.openrewrite.spring5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CustomerServiceContextTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void loadsCustomerServiceFromSpringContext() {
        assertNotNull(customerService);
    }

    @Test
    public void findsSeededCustomers() {
        List<Customer> customers = customerService.findCustomers();

        assertEquals(2, customers.size());
        assertEquals("Ada Lovelace", customerService.findCustomer("C-1001").getName());
    }

    @Test
    public void registersCustomerInRepository() {
        Customer customer = customerService.registerCustomer("C-1003", "Katherine Johnson", "katherine@example.com");

        assertEquals(customer, customerService.findCustomer("C-1003"));
        assertEquals("katherine@example.com", customer.getEmail());
    }
}
