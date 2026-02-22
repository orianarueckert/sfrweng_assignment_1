package com.example.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest_b {

    @Test
    void constructor_quantityMustBePositive() {
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class,
                        () -> new OrderItem("X", 0, 10.0));
        assertEquals("Quantity must be positive", ex.getMessage());
    }
    
    @Test
    void getTotalPrice_returnsQuantityTimesUnitPrice() {
        OrderItem item = new OrderItem("X", 3, 2.5);
        assertEquals(7.5, item.getTotalPrice(), 1e-9);
    }
    
    // new test case
    @Test
    void constructor_unitPriceCannotBeNegative() {
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class,
                        () -> new OrderItem("X", 1, -0.01));
        assertEquals("Unit price cannot be negative", ex.getMessage());
    }
}

