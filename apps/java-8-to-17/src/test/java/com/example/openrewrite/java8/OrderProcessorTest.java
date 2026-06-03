package com.example.openrewrite.java8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderProcessorTest {

    private OrderProcessor processor;

    @BeforeEach
    public void setUp() {
        processor = new OrderProcessor();
    }

    @Test
    public void calculatesOrderTotal() {
        CustomerOrder order = new CustomerOrder("ORD-1001", new Date());
        order.addLine(new OrderLine("BOOK", 2, new BigDecimal("12.50")));
        order.addLine(new OrderLine("PEN", 3, new BigDecimal("1.25")));

        BigDecimal total = processor.calculateTotal(order.getLines());

        assertEquals(new BigDecimal("28.75"), total);
    }

    @Test
    public void marksPaidOrderAndCreatesReceipt() {
        CustomerOrder order = new CustomerOrder("ORD-1002", new Date());
        order.addLine(new OrderLine("MUG", 1, new BigDecimal("9.99")));

        Receipt receipt = processor.process(order);

        assertEquals("ORD-1002", receipt.getOrderNumber());
        assertEquals(OrderStatus.PAID, receipt.getStatus());
        assertEquals(new BigDecimal("9.99"), receipt.getTotal());
        assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    public void detectsOldOrders() {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, -31);
        CustomerOrder oldOrder = new CustomerOrder("ORD-1003", calendar.getTime());

        assertTrue(processor.isOlderThanThirtyDays(oldOrder, now));

        CustomerOrder currentOrder = new CustomerOrder("ORD-1004", new Date());
        assertFalse(processor.isOlderThanThirtyDays(currentOrder, now));
    }

    @Test
    public void buildsHumanReadableSummary() {
        CustomerOrder order = new CustomerOrder("ORD-1005", new Date());
        order.addLine(new OrderLine("BAG", 1, new BigDecimal("25.00")));

        assertEquals("Order ORD-1005 has 1 line(s)", processor.buildSummary(order));
    }
}
