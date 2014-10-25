/**
 * Created by Joshua on 18/10/2014.
 */
public class fuser{

    public int count = gitParser.userCount;
    public int fileCount = gitParser.userFileCount;
    public String[] files = new String [500];
    public int numFiles = 0;
    public void main() {

        //TO-DO: compile a list of all files
        //TO-DO: compute number of changes and number of  contributors
        //going through the array to create a list of all files
        /*for (int i = 0; i < count + 1; i++) {S

            for (int j = 0; i < gitParser.fileArray[i].length; i++){

               int cie = checkIfExist(gitParser.fileArray[i][j]);

               if (cie == 0){
                   files[numFiles] = gitParser.fileArray[i][j];
                   numFiles++;
               }
            }


        }*/
    }

    public int checkIfExist(String filename) {

        for (int n = 0; n < numFiles+1; n++){

            if(filename == files[n]){
                return 1;
            }

        }
        return 0;

    }


}
