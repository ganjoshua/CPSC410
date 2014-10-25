import org.junit.Test;

import static org.junit.Assert.*;

public class fuserTest {
    fuser fs;

    @Test
    public void testCreate() {

        //this test creates a new fuser and asserts that creating it is successful
        fs = new fuser();
        assertNotNull(fs);
        fs = null;

    }

    @Test
    public void testFileExists() {

        //this test creates a new fuser and asserts that creating it is successful
        fs = new fuser();
        int returnVal = fs.checkIfExist("testlog.txt");
        assertTrue(fs.numFiles == 0);
        assertTrue(returnVal == 0);
        fs = null;

    }

}