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
        RuntimeException cause = new RuntimeException("Cause");
        PasswordValidationException exception = new PasswordValidationException("Error", cause);

        assertNotNull(exception.getCause());
        assertEquals("Cause", exception.getCause().getMessage());
    }
}
