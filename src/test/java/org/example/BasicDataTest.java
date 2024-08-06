package org.example;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BasicDataTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private BasicData basicData;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        basicData = new BasicData(1, "John Doe", "password123");
    }

    @Test
    public void testPrint() {
        basicData.print();
        String expectedOutput = "\nID: 1\nName: John Doe\nPassword: password123\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
