package com.order.wise.exceptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class ClientNotFoundExceptionTest {

    @Test
    @DisplayName("Should create ClientNotFoundException with message and cause")
    void constructorWithMessageAndCauseTest() {
        String expectedMessage = "Client with specified ID not found.";
        Exception expectedCause = new NullPointerException("Simulated underlying cause");

        ClientNotFoundException exception = new ClientNotFoundException(expectedMessage, expectedCause);

        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
        assertSame(expectedCause, exception.getCause());
    }
}