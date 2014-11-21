package code;

import info.Files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CreateFileClass {
	public static Files myFiles[] = new Files[100];
	public static int fileCount = 0;
	
	public static void main() throws FileNotFoundException{
		FileReader     input = new FileReader("txtxml/testxml.txt");
		String myLine;
		String cleanString;
		BufferedReader bufRead = new BufferedReader(input);
		
		try {
            while ( (myLine = bufRead.readLine()) != null)
            {
            	cleanString = myLine.substring(0, myLine.indexOf(","));
            	//System.out.println(cleanString);
            	myFiles[fileCount] = new Files();
            	myFiles[fileCount].name = cleanString;
            	myFiles[fileCount].contributors = new int[30];
                fileCount++;  
                //System.out.println(Integer.toString(fileCount));
            }
            
		}catch (IOException e) {
            e.printStackTrace();
        }
	}

}
