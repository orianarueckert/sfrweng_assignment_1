package com.example.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

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
}

