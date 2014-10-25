/**
 * Created by Joshua on 18/10/2014.
 */
public class fuser{

    static int count = gitParser.userCount;
    static int fileCount = gitParser.userFileCount;
    static String[] files = new String [500];
    static int numFiles = 0;
    public static void main() {
        for (int i = 0; i < count + 1; i++) {

            for (int j = 0; i < gitParser.fileArray[i].length; i++){

               int cie = checkIfExist(gitParser.fileArray[i][j]);

               if (cie == 0){
                   files[numFiles] = gitParser.fileArray[i][j];
                   numFiles++;
               }
            }


        }
    }

    private static int checkIfExist(String filename) {

        for (int n = 0; n < numFiles; n++){

            if(filename == files[n]){
                return 1;
            }

        }
        return 0;

    }


}
