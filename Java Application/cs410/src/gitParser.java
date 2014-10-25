import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Joshua on 18/10/2014.
 */
class Users{

    String name;
    String[] fileArray;
    int fileCount;

}
public class gitParser {

    static String[] userArray = new String [100];
    static String [][] fileArray = new String [100][100];
    static int count = 0;
    static int userCount = 0;
    static int userFileCount = 0;

	public static void main() throws FileNotFoundException {

        FileReader     input = new FileReader("log.txt");
        BufferedReader bufRead = new BufferedReader(input);
        String         myLine = null;
        String         currentUser;
        int            currentUserCount = 0;
        Boolean        userAlreadyExist = false;
        Users          user[] = new Users[100];

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

                        currentUser = myLine;
                        //initialize first user and add to the users array
                        if (userCount == 0){

                            user[0] = new Users();
                            user[0].name = currentUser;
                            currentUserCount = 0;
                            userCount++;

                        }else{

                            //checks if user is already recorded
                             for (int i = 0; i< userCount; i++) {

                                 if (currentUser == user[i].name){
                                     userAlreadyExist = true;
                                     currentUserCount = i;
                                 }
                             }

                            //records user if not yet done so
                            if (userAlreadyExist == false){
                                user[userCount] = new Users();
                                user[userCount].name = currentUser;
                                currentUserCount = userCount;
                                userCount++;
                            }else{
                                //do nothing and reset userAlreadyExist
                                userAlreadyExist = false;
                            }
                        }
                     }else if (count == 1){
                        //commit message
                        if (myLine.contains("fix")){
                            //this change was a fix to a bug/error
                        }
                        //read file change
                        myLine = bufRead.readLine();
                        user[currentUserCount].fileArray[user[currentUserCount].fileCount]=myLine;
                        user[currentUserCount].fileCount++;
                        count++;

                    }else if (count > 1){

                        user[currentUserCount].fileArray[user[currentUserCount].fileCount]=myLine;
                        user[currentUserCount].fileCount++;

                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
