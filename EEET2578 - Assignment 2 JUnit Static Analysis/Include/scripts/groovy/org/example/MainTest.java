package org.example;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

public class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testMainAdminFlow() {
        String input = "1\nAdmin1\npass1\n5\n6\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.main(new String[]{});
        assertTrue("Should handle admin login and show total number of students", outContent.toString().contains("Total Number of Students"));
    }

    @Test
    public void testMainStudentFlow() {
        String input = "2\n7654324\np7654324#\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.main(new String[]{});
        assertTrue("Should handle student login and show events", outContent.toString().contains("List of Events"));
    }

    @Test
    public void testMainInvalidOption() {
        String input = "4\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.main(new String[]{});
        assertTrue("Should handle invalid options and exit", outContent.toString().contains("Wrong choice"));
    }

    @Test
    public void testExit() {
        String input = "3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.main(new String[]{});
        assertTrue("Should exit the application", outContent.toString().contains("Thanks for using, Bye!"));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
}
