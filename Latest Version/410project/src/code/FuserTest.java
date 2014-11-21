package code;

import org.junit.Test;

import static org.junit.Assert.*;

public class FuserTest {
    Fuser fs;

    @Test
    public void testCreate() {

        //this test creates a new fuser and asserts that creating it is successful
        fs = new  Fuser();
        assertNotNull(fs);
        fs = null;

    }

}
