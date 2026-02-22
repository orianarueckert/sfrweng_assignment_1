package com.example.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountServiceTest {

    private final DiscountService discountService = new DiscountService();

    @Test
    void applyDiscount_nullCode_returnsSubtotalUnchanged() {
        assertEquals(100.0, discountService.applyDiscount(100.0, null), 1e-9);
    }


    @Test
    void applyDiscount_student10_applies10PercentOff_caseInsensitive() {
        assertEquals(90.0, discountService.applyDiscount(100.0, "student10"), 1e-9);
    }

    @Test
    void applyDiscount_blackFriday_applies30PercentOff() {
        assertEquals(70.0, discountService.applyDiscount(100.0, "BLACKFRIDAY"), 1e-9);
    }


    @Test
    void applyDiscount_invalidCode_throwsIllegalArgumentException() {
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class,
                        () -> discountService.applyDiscount(100.0, "INVALID"));
        assertEquals("Invalid discount code", ex.getMessage());
    }
}
