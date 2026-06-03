package com.example.openrewrite.java8;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerOrder {

    private String orderNumber;
    private Date createdAt;
    private OrderStatus status;
    private List<OrderLine> lines = new ArrayList<OrderLine>();

    public CustomerOrder(String orderNumber, Date createdAt) {
        this.orderNumber = orderNumber;
        this.createdAt = createdAt;
        this.status = OrderStatus.NEW;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    public void addLine(OrderLine line) {
        lines.add(line);
    }
}
