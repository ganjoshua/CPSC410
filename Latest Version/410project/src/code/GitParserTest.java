package code;
import junit.framework.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class GitParserTest {
    GitParser gp;

    @Test
    public void testCreate() {

       //this test creates a new git parser
       gp = new GitParser();
       assertNotNull(gp);
       gp = null;

    }

    @Test
    public void testNameLog() throws FileNotFoundException {

        //this test tests the git parser using a test log file with known info
        //it test if the user names stored in the user array are correct
        gp = new GitParser();
        gp.ResourceFile("txtxml/testlog.txt");
        gp.main();
        assertTrue(gp.user[0].name.equals("Joshua"));
        assertTrue(gp.user[1].name.equals("Chun"));
        assertTrue(gp.user[2].name.equals("Alex"));
        assertTrue(gp.user[3].name.equals("Johnny"));
        
        gp = null;
    }


}
