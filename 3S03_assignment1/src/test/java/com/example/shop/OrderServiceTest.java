package com.example.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private final OrderService orderService = new OrderService();

    @Test
    void processOrder_invalidPayment_setsCancelledAndReturns0() {
        Order order = new Order();
        order.addItem(new OrderItem("A", 1, 100.0));

        double total = orderService.processOrder(order, "STUDENT10", "crypto");

        assertEquals(0.0, total, 1e-9);
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    void processOrder_validPayment_noDiscount_appliesTax_andSetsPaid() {
        Order order = new Order();
        order.addItem(new OrderItem("A", 1, 100.0)); // subtotal 100

        double total = orderService.processOrder(order, null, "card");
        // discountedTotal = 100, tax = 20, final = 120
        assertEquals(120.0, total, 1e-9);
        assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    void processOrder_validPayment_withStudentDiscount_appliesDiscountThenTax() {
        Order order = new Order();
        order.addItem(new OrderItem("A", 2, 50.0)); // subtotal 100

        double total = orderService.processOrder(order, "STUDENT10", "paypal");
        // discountedTotal=90, tax=18, final=108
        assertEquals(108.0, total, 1e-9);
        assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    void processOrder_validPayment_withBlackFriday_appliesDiscountThenTax() {
        Order order = new Order();
        order.addItem(new OrderItem("A", 1, 200.0)); // subtotal 200

        double total = orderService.processOrder(order, "BLACKFRIDAY", "card");
        // discountedTotal=140, tax=28, final=168
        assertEquals(168.0, total, 1e-9);
        assertEquals(OrderStatus.PAID, order.getStatus());
    }


    @Test
    void processOrder_invalidDiscountCode_throwsIllegalArgumentException_andDoesNotSetPaid() {
        Order order = new Order();
        order.addItem(new OrderItem("A", 1, 100.0));

        assertThrows(IllegalArgumentException.class,
                () -> orderService.processOrder(order, "INVALID", "card"));

        // Discount exception happens before status update to PAID
        assertEquals(OrderStatus.CREATED, order.getStatus());
    }


}

