package org.example;

import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class BasicDataTest {
    private BasicData basicData;

    @Before
    public void setUp() {
        basicData = new BasicData(1, "John Doe", "password123");
    }

    @Test
    public void testGetID() {
        assertEquals(1, basicData.getID());
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", basicData.getName());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password123", basicData.getPassword());
    }

    @Test
    public void testSetID() {
        basicData.setID(2);
        assertEquals(2, basicData.getID());
    }

    @Test
    public void testSetName() {
        basicData.setName("Jane Smith");
        assertEquals("Jane Smith", basicData.getName());
    }

    @Test
    public void testSetPassword() {
        basicData.setPassword("newpassword");
        assertEquals("newpassword", basicData.getPassword());
    }

    @Test
    public void testPrint() {
        // Capture the print output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        basicData.print();
        String expectedOutput = "\nID: 1\nName: John Doe\nPassword: password123\n";
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out); // Restore original System.out
    }
}
