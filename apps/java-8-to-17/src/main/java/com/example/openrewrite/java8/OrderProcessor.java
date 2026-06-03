package com.example.openrewrite.java8;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class OrderProcessor {

    private final Clock clock;

    public OrderProcessor() {
        this(Clock.systemDefaultZone());
    }

    public OrderProcessor(Clock clock) {
        this.clock = clock;
    }

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

        LocalDate processedDate = LocalDate.now(clock);
        return new Receipt(order.getOrderNumber(), processedDate, total, order.getStatus());
    }

    public BigDecimal calculateTotal(List<OrderLine> lines) {
        return lines.stream()
                .filter(line -> line.getQuantity() > 0)
                .map(OrderLine::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean isOlderThanThirtyDays(CustomerOrder order, LocalDate today) {
        return order.getCreatedAt().isBefore(today.minusDays(30));
    }

    public String buildSummary(CustomerOrder order) {
        String skus = order.getLines().stream()
                .map(OrderLine::getSku)
                .collect(Collectors.joining(", "));
        return "Order %s has %d line(s): %s".formatted(
                order.getOrderNumber(),
                order.getLines().size(),
                skus
        );
    }
}
