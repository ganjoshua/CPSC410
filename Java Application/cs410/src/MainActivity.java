import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Joshua on 25/10/2014.
 */
public class MainActivity {

    public static void main() throws IOException {

        gitParser gParse = new gitParser();
        countLinesFromFile lineCounter = new countLinesFromFile();
        fuser myFuser = new fuser();

        gParse.main();
        lineCounter.main();
        myFuser.main();

    }

}
