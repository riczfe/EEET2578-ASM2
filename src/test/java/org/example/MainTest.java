package org.example;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

public class MainTest {
    // Initialize output capture
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    // Initialize input redirection
    private final InputStream originalIn = System.in;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent)); // Redirects System.out to capture output
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut); // Restores original System.out
        System.setIn(originalIn);   // Restores original System.in
    }

    @Test
    public void testMainExit() {
        // Simulate user input that causes the application to exit
        String input = "3\n"; // Assuming '3' is the command to exit in the main method
        System.setIn(new ByteArrayInputStream(input.getBytes())); // Redirects System.in to feed in the exit command

        Main.main(new String[]{}); // Execute the main method

        // Check the output for the expected exit message
        assertTrue("Output should contain exit message", outContent.toString().contains("Thanks for using, Bye!"));
    }
}
