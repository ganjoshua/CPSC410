import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Joshua on 25/10/2014.
 */
public class MainActivity {
	
	public static final MODE = FB;
	//MODE = JP for jpaman, FB for facebook for android

    public static void main() throws IOException {

        gitParser gParse = new gitParser();
        countLinesFromFile lineCounter = new countLinesFromFile();
        fuser myFuser = new fuser();

		//TO-DO: set up to analyse either FB or Jpacman
		if (MODE == FB{
			//gParse.ResourceFile = "fbLog.txt"
			gParse.main();
		} else{
			//gParse.ResourceFile = "jpLog.txt"
			gParse.main();
		}
		
		//TO-DO: repeat lineCounter for all files in code base
		lineCounter.ResourceFile = "insert file name here";
        lineCounter.main();
		
        myFuser.main();
		
		//TO-DO: Add call to visualizer

    }

}
