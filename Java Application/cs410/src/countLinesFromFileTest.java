import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class countLinesFromFileTest {
    countLinesFromFile lc;

    @Test
    public void testCreate() {

        //this test creates a new count lines from file java class
        // asserts that creation is successful
        lc = new countLinesFromFile();
        assertNotNull(lc);
        lc = null;

    }

    @Test
    public void testCount() throws IOException {

        //Using a test file with a known number of lines
        //Assert if the line counter obtains the correct value
        lc = new countLinesFromFile();
        lc.ResourceFile("testlog.text");
        lc.main();
        assertTrue(lc.numLines == 44);


    }



}