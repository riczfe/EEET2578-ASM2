package org.example;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.*;

public class EventTest {
    private Event event;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @Before
    public void setUp() throws Exception {
        resetFiles();
        event = new Event();  // Initializes the Event object and loads data
        System.setOut(new PrintStream(outContent));
    }

    private void resetFiles() throws Exception {
        // Reset the student file to initial state
        String studentContent = "Id:7654324, Name:Student1, Password:p7654324#\n" +
                "Id:7654325, Name:Student2, Password:p7654325#\n" +
                "Id:7654326, Name:Student3, Password:p7654326#\n" +
                "Id:7654327, Name:Student4, Password:p7654327#\n" +
                "Id:7654328, Name:Student5, Password:p7654328#\n" +
                "Id:7654329, Name:Student6, Password:p7654329#\n" +
                "Id:7654330, Name:Student7, Password:p7654330#\n" +
                "Id:7654331, Name:Student8, Password:p7654331#\n" +
                "Id:7654332, Name:Student9, Password:p7654332#\n";
        Files.write(Paths.get("student.txt"), studentContent.getBytes());

        // Reset the admin file if necessary
        String adminContent = "Id:1, Name:Admin1, Password:pass1\n" +
                "Id:2, Name:Admin2, Password:pass2\n" +
                "Id:3, Name:Admin3, Password:pass3\n";
        Files.write(Paths.get("admin.txt"), adminContent.getBytes());
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
        assertTrue("Should successfully remove an existing student", event.removeStudent(7654324));
    }

    @Test
    public void testRemoveStudent_Failure() {
        assertFalse("Should fail to remove a non-existing student", event.removeStudent(9999999));
    }

    @Test
    public void testCountStudent() {
        assertEquals("Should count the correct number of students", 9, event.countStudent());
    }

    @Test
    public void testAddStudent_Success() {
        String input = "9999\nNew Student\np1234567#\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertEquals("Student Added Successfully", event.AddStudent());
    }

    @Test
    public void testAddStudent_Failure() {
        String input = "7654324\nExisting Student\np7654324#\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertEquals("Student Exists", event.AddStudent());
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(originalIn);  // Restore standard input
        System.setOut(originalOut);  // Restore standard output
    }
}
