import org.junit.Before;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class gitParser {

   // static String[] userArray = new String [100];
   //static String [][] fileArray = new String [100][100];
    public Users user[] = new Users[100];
    public static int count = 0;
    public static int userCount = 0;
    public static int userFileCount = 0;
    public static String logFile;

    /*public static void init(){
        gitParser gp = new gitParser();
    }*/

    public void ResourceFile(String res)
    {

        this.logFile = res;
    }

    public void main() throws FileNotFoundException {

        FileReader     input = new FileReader(logFile);
        BufferedReader bufRead = new BufferedReader(input);
        String         myLine;
        String         currentUser;
        int            currentUserCount = 0;
        Boolean        userAlreadyExist = false;
        Boolean        fileAlreadyExist = false;
        String         fileName;


        //TO-DO: add files to Files class when parsing the log file
        try {
            while ( (myLine = bufRead.readLine()) != null)
            {
                if (myLine.equals("new")){
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

                                 if (currentUser.equals(user[i].name)){
                                     userAlreadyExist = true;
                                     currentUserCount = i;
                                 }
                             }

                            //records user if not yet done so
                            if (!userAlreadyExist){
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
                            //TO-DO: implement something to record this
                        }
                        //read file change
                        /*myLine = bufRead.readLine();
                        user[currentUserCount].fileArray[user[currentUserCount].fileCount]=myLine;
                        user[currentUserCount].fileCount++;*/
                        count++;

                    }else if (count > 1){
                       fileName = myLine;

                        for (int i = 0; i < user[currentUserCount].fileCount; i++){

                            if(fileName.equals(user[currentUserCount].fileArray[i])){
                                fileAlreadyExist = true;
                            }

                        }

                        if(!fileAlreadyExist) {

                            //add fileName to user's fileArray
                            user[currentUserCount].fileArray[user[currentUserCount].fileCount] = fileName;
                            user[currentUserCount].fileCount++;

                        }else{

                            //do nothing, reset fileAlreadyExist
                            fileAlreadyExist = false;
                        }
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
