package code;
import info.Users;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GitParser {

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

        GitParser.logFile = res;
        System.out.println("Set res file for git parser:" + res);
        
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
        String 		   cleanFileName;
        String		   finalFileName;
        Boolean		   fileFix = null;
       

        //TO-DO: add files to Files class when parsing the log file
        try {
            while ( (myLine = bufRead.readLine()) != null)
            {
                if (myLine.equals("new") || myLine.length() == 0){
                    //new commit history
                    count = 0;
                    //System.out.println("new");
                }
                else{
                    //commit info
                    if (count == 0){

                        currentUser = myLine;
                        //initialize first user and add to the users array
                        if (userCount == 0){

                            user[0] = new Users();
                            user[0].name = currentUser;
							user[0].userNum = 0;
							user[0].fileCount = 0;
							user[0].fileArray = new String[100];
							System.out.println("User " + Integer.toString(userCount) + " created:" + currentUser);
                            currentUserCount = 0;
                            userCount++;
                            count++;

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
								user[userCount].userNum = userCount;
								user[userCount].fileCount = 0;
								user[userCount].fileArray = new String[100];
                                currentUserCount = userCount;
                                System.out.println("User " + Integer.toString(userCount) + " created:" + currentUser);
                                userCount++;
                                count++;
                            }else{
                                //do nothing and reset userAlreadyExist
                                userAlreadyExist = false;
                                count++;
                            }
                        }
                     }else if (count == 1){
                        //commit message
                        if (myLine.contains("fix")){
                            //this change was a fix to a bug/error
                            //TO-DO: implement something to record this
                        	//System.out.println("This is a fix");
                        	fileFix = true;
                        }else{
							
							//TO-DO: record change in Files struct for all files involved
                        	//System.out.println("This is not a fix");
                        	fileFix = false;
						}
                        count++;

                    }else if (count > 1){
                       
                    	if(myLine.length() != 0 && myLine.startsWith("JPacman-undo/src/main/java/ca/ubc/jpacman")){
                    		fileName = myLine.replace("/", ".");;
                    		cleanFileName = fileName.replace("JPacman-undo.src.main.java.","");
                    		finalFileName = cleanFileName.replace(".java", "");
                    		
                    		//System.out.println(fileName);
                            //System.out.println("Current User Count:" + Integer.toString(currentUserCount));
                             for (int i = 0; i < user[currentUserCount].fileCount; i++){

                                 if(finalFileName.equals(user[currentUserCount].fileArray[i])){
                                     fileAlreadyExist = true;
                                 }

                             }

                             if(!fileAlreadyExist) {

                                 //add fileName to user's fileArray
                                 user[currentUserCount].fileArray[user[currentUserCount].fileCount] = finalFileName;
                                 user[currentUserCount].fileCount++;

                             }else{

                                 //do nothing, reset fileAlreadyExist
                                 fileAlreadyExist = false;
                             }
                             
                             for (int k=0; k<CreateFileClass.fileCount; k++){
                 	        	if (CreateFileClass.myFiles[k].name.equals(finalFileName)){
                 	        		
                 	        		if (!fileFix) CreateFileClass.myFiles[k].numChanges++;
                 	        		int numCon = CreateFileClass.myFiles[k].numContributors;
                 	        		
                 	        		for (int nc = 0; nc < numCon; nc++){
                 	        			
                 	        			if (CreateFileClass.myFiles[k].contributors[nc] != currentUserCount){
                 	        				CreateFileClass.myFiles[k].contributors[numCon] = currentUserCount;
                 	        				CreateFileClass.myFiles[k].numContributors++;
                 	        			}
                 	        			
                 	        		}
                 	        		
                 	        		
                 	        	}else{
                 	        		
                 	        	}
                 	        }
                             
                    	}else{
                    		//do nothing
                    	}
                    	
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
