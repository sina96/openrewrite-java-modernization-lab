package com.example.openrewrite.java8;

import java.math.BigDecimal;

public class Receipt {

    private final String orderNumber;
    private final String processedDate;
    private final BigDecimal total;
    private final OrderStatus status;

    public Receipt(String orderNumber, String processedDate, BigDecimal total, OrderStatus status) {
        this.orderNumber = orderNumber;
        this.processedDate = processedDate;
        this.total = total;
        this.status = status;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getProcessedDate() {
        return processedDate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
