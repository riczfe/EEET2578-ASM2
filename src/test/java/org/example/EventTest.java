package org.example;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class EventTest {
    private Event event;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @Before
    public void setUp() {
        event = new Event();  // Initializes the Event object and loads data
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testAdminLogin_Success() {
        String input = "Admin1\npass1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertTrue("Admin login should succeed with correct credentials", event.AdminLogin());
    }

    @Test
    public void testAdminLogin_Failure() {
        String input = "Admin1\nwrongpass\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertFalse("Admin login should fail with incorrect credentials", event.AdminLogin());
    }

    @Test
    public void testStudentLogin_Success() {
        String input = "7654324\np7654324#\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertTrue("Student login should succeed with correct ID and password", event.StudentLogin());
    }

    @Test
    public void testStudentLogin_Failure() {
        String input = "7654324\nwrongpass\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertFalse("Student login should fail with incorrect password", event.StudentLogin());
    }

    @Test
    public void testShowStudentEvents() {
        event.showStudentEvents();
        assertTrue("Should display student events", outContent.toString().contains("Wild Hope"));
    }

    @Test
    public void testViewStudentDetails() {
        assertTrue("Should return true if student details are displayed correctly", event.viewStudentDetails());
    }

    @Test
    public void testSearchStudentDetails_Success() {
        assertTrue("Should find student details for a valid ID", event.searchStudentDetails(7654324));
    }

    @Test
    public void testSearchStudentDetails_Failure() {
        assertFalse("Should not find student details for an invalid ID", event.searchStudentDetails(9999999));
    }

    @Test
    public void testRemoveStudent_Success() {
        // Ensure the student exists before attempting to remove
        assertTrue("Should successfully remove an existing student", event.removeStudent(7654324));
    }

    @Test
    public void testRemoveStudent_Failure() {
        assertFalse("Should fail to remove a non-existing student", event.removeStudent(9999999));
    }

    @Test
    public void testCountStudent() {
        // Count should match the initial state plus any changes made by successful add/remove tests
        assertEquals("Should count the correct number of students", 9, event.countStudent());
    }

    @Test
    public void testAddStudent_Success() {
        String input = "9998\nNew Student\np1234567#\n"; // Use a unique ID to ensure success
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertEquals("Student Added Successfully", event.AddStudent());
    }

    @Test
    public void testAddStudent_Failure() {
        String input = "7654324\nExisting Student\np7654324#\n"; // Attempt to add an existing ID
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertEquals("Student Exists", event.AddStudent());
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(originalIn);  // Restore standard input
        System.setOut(originalOut);  // Restore standard output
    }
}
