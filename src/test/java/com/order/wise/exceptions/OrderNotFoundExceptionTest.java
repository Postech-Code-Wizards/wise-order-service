package com.order.wise.exceptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class OrderNotFoundExceptionTest {

    @Test
    @DisplayName("Should raise OrderNotFoundException with null message")
    void constructorWithNullMessageTest() {

        OrderNotFoundException exception = new OrderNotFoundException(null);

        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "Order with the provided ID was not found."})
    @DisplayName("Should raise OrderNotFoundException with empty or specific message")
    void constructorWithNonNullMessageTest(String expectedMessage) {
        OrderNotFoundException exception = new OrderNotFoundException(expectedMessage);

        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
        assertNull(exception.getCause());
    }
}
