package code;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CountLinesFromFileTest {
    CountLinesFromFile lc;

    @Test
    public void testCreate() {

        //this test creates a new count lines from file java class
        // asserts that creation is successful
        lc = new CountLinesFromFile();
        assertNotNull(lc);
        lc = null;

    }

    @Test
    public void testCount() throws IOException {

        //Using a test file with a known number of lines
        //Assert if the line counter obtains the correct value
        lc = new CountLinesFromFile();
        lc.ResourceFile("txtxml/testlog.txt");
        lc.main();
        System.out.println(Integer.toString(lc.numLines));
        assertTrue(lc.numLines == 37);


    }



}
