package com.example.openrewrite.java8;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Receipt {

    private final String orderNumber;
    private final LocalDate processedDate;
    private final BigDecimal total;
    private final OrderStatus status;

    public Receipt(String orderNumber, LocalDate processedDate, BigDecimal total, OrderStatus status) {
        this.orderNumber = orderNumber;
        this.processedDate = processedDate;
        this.total = total;
        this.status = status;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public LocalDate getProcessedDate() {
        return processedDate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
