import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Joshua on 18/10/2014.
 */
public class gitParser {

    static String[] userArray = new String [100];
    static String [][] fileArray = new String [100][1000];
    static int count = 0;
    static int userCount = 0;
    static int userFileCount = 0;

    public static void main() throws FileNotFoundException {

        FileReader input = new FileReader("log.txt");
        BufferedReader bufRead = new BufferedReader(input);
        String myLine = null;

        try {
            while ( (myLine = bufRead.readLine()) != null)
            {
                if (myLine == "new"){
                    //new commit history
                    count = 0;
                }
                else{
                    //commit info
                    if (count == 0){
                        //name of person who made commit
                        userArray[userCount]= myLine;
                        userCount++;
                        count++;
                    }

                    if (count == 1){
                        //commit message
                        if (myLine.contains("fix")){
                            //this change was a fix to a bug/error
                        }
                        //read file change
                        myLine = bufRead.readLine();
                        fileArray [userCount][userFileCount] = myLine;
                        userFileCount++;
                        count++;
                    }

                    if (count > 1){

                        fileArray [userCount][userFileCount] = myLine;
                        userFileCount++;

                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
