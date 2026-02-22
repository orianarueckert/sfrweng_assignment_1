package com.example.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentValidatorTest {

    private final PaymentValidator validator = new PaymentValidator();

    @Test
    void isPaymentMethodValid_null_returnsFalse() {
        assertFalse(validator.isPaymentMethodValid(null));
    }

    @Test
    void isPaymentMethodValid_card_returnsTrue_caseInsensitive() {
        assertTrue(validator.isPaymentMethodValid("CARD"));
    }

    @Test
    void isPaymentMethodValid_paypal_returnsTrue() {
        assertTrue(validator.isPaymentMethodValid("paypal"));
    }

    @Test
    void isPaymentMethodValid_crypto_returnsFalse() {
        assertFalse(validator.isPaymentMethodValid("crypto"));
    }

    @Test
    void isPaymentMethodValid_unknown_throwsUnsupportedOperationException() {
        UnsupportedOperationException ex =
                assertThrows(UnsupportedOperationException.class,
                        () -> validator.isPaymentMethodValid("cash"));
        assertEquals("Unknown payment method", ex.getMessage());
    }
}
