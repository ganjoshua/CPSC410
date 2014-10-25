import junit.framework.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class gitParserTest {
    gitParser gp;

    @Test
    public void testCreate() {

       //this test creates a new git parser
       gp = new gitParser();
       assertNotNull(gp);
       gp = null;

    }

    @Test
    public void testNameLog() throws FileNotFoundException {

        //this test tests the git parser using a test log file with known info
        //it test if the user names stored in the user array are correct
        gp = new gitParser();
        gp.ResourceFile("testlog.txt");
        gp.main();
        assertTrue(gp.user[0].name.equals("Joshua"));
        assertTrue(gp.user[1].name.equals("Chun"));
        assertTrue(gp.user[2].name.equals("Alex"));
        assertTrue(gp.user[3].name.equals("Johnny"));
        gp = null;
    }

    @Test
    public void testFileLog() throws FileNotFoundException {

        //This is a test to check if the right file names are stored in user's file array
        //using a known dummy log file
        gp = new gitParser();
        gp.ResourceFile("testlog.txt");
        gp.main();

        assertTrue(gp.user[0].fileCount == 3);
        assertTrue(gp.user[0].fileArray[0].equals("sprite.java"));
        assertTrue(gp.user[0].fileArray[0].equals("score.java"));
        assertTrue(gp.user[0].fileArray[0].equals("game.java"));

        gp = null;

    }

}