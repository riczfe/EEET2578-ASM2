package org.example;

import org.junit.*;
import static org.junit.Assert.*;

public class PasswordValidationExceptionTest {

    @Test
    public void testExceptionMessage() {
        Exception exception = assertThrows(PasswordValidationException.class, () -> {
            throw new PasswordValidationException("Custom message", new RuntimeException());
        });
        assertEquals("Custom message", exception.getMessage());
    }

    @Test
    public void testExceptionCause() {
        Throwable cause = new RuntimeException("Cause");
        PasswordValidationException exception = new PasswordValidationException("Message", cause);
        assertEquals(cause, exception.getCause());
    }
}
