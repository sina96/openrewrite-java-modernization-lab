package com.example.openrewrite.java8;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderProcessor {

    private static final String RECEIPT_DATE_FORMAT = "yyyy-MM-dd";

    public Receipt process(CustomerOrder order) {
        if (order == null) {
            throw new IllegalArgumentException("order is required");
        }

        BigDecimal total = calculateTotal(order.getLines());
        if (total.compareTo(BigDecimal.ZERO) <= 0) {
            order.setStatus(OrderStatus.CANCELLED);
        } else {
            order.setStatus(OrderStatus.PAID);
        }

        SimpleDateFormat formatter = new SimpleDateFormat(RECEIPT_DATE_FORMAT);
        String processedDate = formatter.format(new Date());
        return new Receipt(order.getOrderNumber(), processedDate, total, order.getStatus());
    }

    public BigDecimal calculateTotal(List<OrderLine> lines) {
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < lines.size(); i++) {
            OrderLine line = lines.get(i);
            if (line.getQuantity() > 0) {
                total = total.add(line.getLineTotal());
            }
        }
        return total;
    }

    public boolean isOlderThanThirtyDays(CustomerOrder order, Date now) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        return order.getCreatedAt().before(calendar.getTime());
    }

    public String buildSummary(CustomerOrder order) {
        String summary = "";
        summary = summary + "Order ";
        summary = summary + order.getOrderNumber();
        summary = summary + " has ";
        summary = summary + order.getLines().size();
        summary = summary + " line(s)";
        return summary;
    }
}
