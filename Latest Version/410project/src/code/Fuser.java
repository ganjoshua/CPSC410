/* Code for writeToTxtFile() modified from here: 
 * http://stackoverflow.com/questions/11100381/to-edit-a-specific-line-in-a-textfile-using-java-program
 */
package code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fuser{

    //public int count = GitParser.userCount;
    //public int fileCount = GitParser.userFileCount;
    //public String[] files = new String [500];
    public int numFiles = 0;
    
    public void main() throws IOException {
    	int nContributors;
    	int nChanges;
    	int bRank;
    	int nLines;
    	int probBugs;
    	
    	for (int k=0; k<CreateFileClass.fileCount; k++){
    		nContributors = CreateFileClass.myFiles[k].numContributors;
    		nChanges = CreateFileClass.myFiles[k].numChanges;
    		bRank = CreateFileClass.myFiles[k].bugRank;
    		nLines = CreateFileClass.myFiles[k].numLines;
    		
    		probBugs = 2*nContributors + 3*nChanges + 1*nLines + 5*bRank;
    		CreateFileClass.myFiles[k].pBugs = probBugs;
    	}
    	
    	normalizePBugs();
    	writeToTxtFile();
    }

	private void normalizePBugs() {
		int min = 0;
		int max = 0;
		int init = 0;
		
		for (int k=0; k<CreateFileClass.fileCount; k++){
			int currentPBugs = CreateFileClass.myFiles[k].pBugs;
			if (init == 0 ){
				min = currentPBugs;
				max = currentPBugs;
				init = 1;
			}else{
				if (currentPBugs < min){
					min = currentPBugs;
				}else if (currentPBugs > max){
					max = currentPBugs;
				}
			}
		}
		
		for (int k=0; k<CreateFileClass.fileCount; k++){
			int currentPBugs = CreateFileClass.myFiles[k].pBugs;
			float calcPBugs = (float)1 + 9*((float)(currentPBugs - min)/(float)(max - min));
			//System.out.println(Float.toString(calcPBugs));
			int npb = (int)Math.round(calcPBugs);
			CreateFileClass.myFiles[k].normPBugs  = npb;
			
			switch (npb) {
				case 1: CreateFileClass.myFiles[k].nPBval = "a";
						break;
				case 2: CreateFileClass.myFiles[k].nPBval = "b";
						break;
				case 3: CreateFileClass.myFiles[k].nPBval = "c";
						break;
				case 4: CreateFileClass.myFiles[k].nPBval = "d";
						break;
				case 5: CreateFileClass.myFiles[k].nPBval = "e";
						break;
				case 6: CreateFileClass.myFiles[k].nPBval = "f";
						break;
				case 7: CreateFileClass.myFiles[k].nPBval = "g";
						break;
				case 8: CreateFileClass.myFiles[k].nPBval = "h";
						break;
				case 9: CreateFileClass.myFiles[k].nPBval = "i";
						break;
				case 10: CreateFileClass.myFiles[k].nPBval = "j";
						break;
			
			}
			System.out.println(Integer.toString((int)Math.round(calcPBugs)));
		}
		
	}

	private void writeToTxtFile() throws IOException {
		FileInputStream fstream = new FileInputStream("txtxml/testxml.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		
        String strLine;
        String strFileName;
        String newStrLine;
        StringBuilder fileContent = new StringBuilder();
        
        while ((strLine = br.readLine()) != null) {
            // Print the content on the console
            //System.out.println(strLine);
            strFileName = strLine.substring(0, strLine.indexOf(","));
                       
            for (int k=0; k<CreateFileClass.fileCount; k++){
            	if (strFileName.equals(CreateFileClass.myFiles[k].name)){
            		newStrLine = CreateFileClass.myFiles[k].nPBval + ", " + strLine;
					fileContent.append(newStrLine);
                    fileContent.append("\n");
            	}
            }
           
         }
        System.out.println("Output newtestxml.txt");
        File nlogFile = new File("txtxml/newtestxml.txt");
        FileWriter fstreamWrite = new FileWriter(nlogFile);
        BufferedWriter out = new BufferedWriter(fstreamWrite);
        out.write(fileContent.toString());
        out.close();
        br.close();
        
	}
    
    
}

