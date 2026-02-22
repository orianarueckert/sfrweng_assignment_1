package com.example.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void newOrder_startsCreated() {
        Order order = new Order();
        assertEquals(OrderStatus.CREATED, order.getStatus());
    }

    
    @Test
    void addItem_whenCreated_addsSuccessfully() {
        Order order = new Order();
        order.addItem(new OrderItem("A", 1, 10.0));
        assertEquals(1, order.getItems().size());
    }
    

    @Test
    void addItem_afterProcessed_throwsIllegalStateException() {
        Order order = new Order();
        order.setStatus(OrderStatus.PAID);

        IllegalStateException ex =
                assertThrows(IllegalStateException.class,
                        () -> order.addItem(new OrderItem("A", 1, 10.0)));
        assertEquals("Cannot add items once order is processed", ex.getMessage());
    }
}

