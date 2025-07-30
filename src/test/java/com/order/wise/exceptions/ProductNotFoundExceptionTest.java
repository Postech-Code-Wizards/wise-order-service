package com.order.wise.exceptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class ProductNotFoundExceptionTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "Product not available in stock.", "Product with specified SKU not found."})
    @DisplayName("Should create ProductNotFoundException with message and cause")
    void constructorWithMessageAndCauseTest(String expectedMessage) {
        Exception expectedCause = new IllegalArgumentException("Invalid input data");

        ProductNotFoundException exception = new ProductNotFoundException(expectedMessage, expectedCause);

        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
        assertSame(expectedCause, exception.getCause());
    }

    @Test
    @DisplayName("Should create ProductNotFoundException with a null message")
    void constructorWithNullMessageTest() {
        String expectedMessage = null;

        ProductNotFoundException exception = new ProductNotFoundException(expectedMessage);

        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
        assertNull(exception.getCause());
    }

}