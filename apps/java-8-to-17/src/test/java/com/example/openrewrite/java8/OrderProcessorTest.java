package com.example.openrewrite.java8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class OrderProcessorTest {

    private OrderProcessor processor;

    @BeforeEach
    public void setUp() {
        Clock fixedClock = Clock.fixed(Instant.parse("2026-06-03T10:15:30Z"), ZoneOffset.UTC);
        processor = new OrderProcessor(fixedClock);
    }

    @ParameterizedTest(name = "{0} totals {1}")
    @MethodSource("orderTotalCases")
    @DisplayName("calculates totals for payable order lines")
    public void calculatesTotalForPayableOrderLines(CustomerOrder order, BigDecimal expectedTotal) {
        BigDecimal total = processor.calculateTotal(order.getLines());

        assertEquals(expectedTotal, total);
    }

    @Test
    @DisplayName("marks an order as paid and records the processing date")
    public void marksOrderAsPaidAndRecordsProcessingDate() {
        CustomerOrder order = new CustomerOrder("ORD-1002", LocalDate.of(2026, 6, 1));
        order.addLine(new OrderLine("MUG", 1, new BigDecimal("9.99")));

        Receipt receipt = processor.process(order);

        assertEquals("ORD-1002", receipt.getOrderNumber());
        assertEquals(OrderStatus.PAID, receipt.getStatus());
        assertEquals(new BigDecimal("9.99"), receipt.getTotal());
        assertEquals(LocalDate.of(2026, 6, 3), receipt.getProcessedDate());
        assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    @DisplayName("cancels an order when every line has a non-payable quantity")
    public void cancelsOrderWhenEveryLineHasNonPayableQuantity() {
        CustomerOrder order = new CustomerOrder("ORD-1003", LocalDate.of(2026, 6, 1));
        order.addLine(new OrderLine("MUG", 0, new BigDecimal("9.99")));

        Receipt receipt = processor.process(order);

        assertEquals(OrderStatus.CANCELLED, receipt.getStatus());
        assertEquals(BigDecimal.ZERO, receipt.getTotal());
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    @ParameterizedTest(name = "order from {0} older than 30 days: {1}")
    @CsvSource({
            "2026-05-03,true",
            "2026-05-04,false",
            "2026-06-03,false"
    })
    @DisplayName("detects orders older than thirty days")
    public void detectsOrdersOlderThanThirtyDays(LocalDate createdAt, boolean expectedOld) {
        CustomerOrder order = new CustomerOrder("ORD-1004", createdAt);
        LocalDate today = LocalDate.of(2026, 6, 3);

        assertEquals(expectedOld, processor.isOlderThanThirtyDays(order, today));
    }

    @Test
    @DisplayName("keeps the thirty-day boundary inclusive")
    public void keepsThirtyDayBoundaryInclusive() {
        CustomerOrder order = new CustomerOrder("ORD-1005", LocalDate.of(2026, 5, 4));

        assertFalse(processor.isOlderThanThirtyDays(order, LocalDate.of(2026, 6, 3)));
    }

    @Test
    @DisplayName("builds a readable summary with ordered SKUs")
    public void buildsReadableSummaryWithOrderedSkus() {
        CustomerOrder order = new CustomerOrder("ORD-1006", LocalDate.of(2026, 6, 3));
        order.addLine(new OrderLine("BAG", 1, new BigDecimal("25.00")));
        order.addLine(new OrderLine("TAG", 2, new BigDecimal("1.50")));

        assertEquals("Order ORD-1006 has 2 line(s): BAG, TAG", processor.buildSummary(order));
    }

    private static Stream<Arguments> orderTotalCases() {
        CustomerOrder mixedOrder = new CustomerOrder("ORD-1001", LocalDate.of(2026, 6, 1));
        mixedOrder.addLine(new OrderLine("BOOK", 2, new BigDecimal("12.50")));
        mixedOrder.addLine(new OrderLine("PEN", 3, new BigDecimal("1.25")));

        CustomerOrder orderWithIgnoredLine = new CustomerOrder("ORD-1007", LocalDate.of(2026, 6, 1));
        orderWithIgnoredLine.addLine(new OrderLine("MUG", 1, new BigDecimal("9.99")));
        orderWithIgnoredLine.addLine(new OrderLine("DAMAGED", 0, new BigDecimal("100.00")));

        return Stream.of(
                Arguments.of(mixedOrder, new BigDecimal("28.75")),
                Arguments.of(orderWithIgnoredLine, new BigDecimal("9.99"))
        );
    }
}
