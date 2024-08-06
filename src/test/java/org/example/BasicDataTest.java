package org.example;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BasicDataTest {
    private BasicData basicData;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        basicData = new BasicData(1, "John Doe", "password123");
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testGetID() {
        assertEquals("getID should return correct ID", 1, basicData.getID());
    }

    @Test
    public void testGetName() {
        assertEquals("getName should return correct name", "John Doe", basicData.getName());
    }

    @Test
    public void testGetPassword() {
        assertEquals("getPassword should return correct password", "password123", basicData.getPassword());
    }

    @Test
    public void testSetID() {
        basicData.setID(2);
        assertEquals("setID should update ID correctly", 2, basicData.getID());
    }

    @Test
    public void testSetName() {
        basicData.setName("Jane Doe");
        assertEquals("setName should update name correctly", "Jane Doe", basicData.getName());
    }

    @Test
    public void testSetPassword() {
        basicData.setPassword("newpassword123");
        assertEquals("setPassword should update password correctly", "newpassword123", basicData.getPassword());
    }

    @Test
    public void testPrint() {
        basicData.print();
        String expectedOutput = "\nID: 1\nName: John Doe\nPassword: password123\n";
        assertEquals("print should output correct information", expectedOutput, outContent.toString());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
