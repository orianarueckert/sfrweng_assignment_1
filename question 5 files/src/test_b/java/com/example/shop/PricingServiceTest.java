package com.example.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PricingServiceTest_b {

    private final PricingService pricingService = new PricingService();

    @Test
    void calculateSubtotal_emptyOrder_returns0() {
        Order order = new Order();
        assertEquals(0.0, pricingService.calculateSubtotal(order), 1e-9);
    }

    @Test
    void calculateSubtotal_multipleItems_sumsCorrectly() {
        Order order = new Order();
        order.addItem(new OrderItem("A", 2, 10.0)); // 20
        order.addItem(new OrderItem("B", 1, 5.5));  // 5.5
        assertEquals(25.5, pricingService.calculateSubtotal(order), 1e-9);
    }

    @Test
    void calculateTax_negativeSubtotal_throwsIllegalArgumentException() {
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class,
                        () -> pricingService.calculateTax(-1.0));
        assertEquals("Subtotal cannot be negative", ex.getMessage());
    }

    @Test
    void calculateTax_positiveSubtotal_returns20Percent() {
        assertEquals(20.0, pricingService.calculateTax(100.0), 1e-9);
    }
    
    // new test case
    @Test
    void calculateTax_zeroSubtotal_returns0() {
        assertEquals(0.0, pricingService.calculateTax(0.0), 1e-9);
    }
}

