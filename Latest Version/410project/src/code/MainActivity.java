package code;

import java.io.IOException;

import parser.ReadBugXMLFile;
import parser.ReadXMLFile;

public class MainActivity {
	
	public static String MODE = "JP";
	public static String fileLoc;
	public static String fileName;
	public static String slashFileName;
	//MODE = JP for jpacman, JOD for JOD DocumentConverter

    public static void main(String argv[]) throws IOException {

    	
    	if (MODE.equals("JP")){
    		
    		//Read classcycle xml to obtain file names by parsing the XML files
    		ReadXMLFile.main("JPacman");
    		
    		//create a Files entry for each relevant file
    		CreateFileClass.main();
    		
    		//count lines for each Files entry
    		CountLinesFromFile lineCounter = new CountLinesFromFile();
    		for (int k=0; k<CreateFileClass.fileCount; k++){
    			fileName = CreateFileClass.myFiles[k].name;
    			//System.out.println(fileName);
    			slashFileName = fileName.replace(".", "/");
    			//System.out.println(slashFileName);
    			fileLoc = "JPacman/src/main/java/" + slashFileName + ".java";
    			//System.out.println(fileLoc);
                lineCounter.ResourceFile(fileLoc);
                CreateFileClass.myFiles[k].numLines =lineCounter.main();
      		}
    		
    		
    		ReadBugXMLFile.main("JPacman");
    		GitParser gParse = new GitParser();
            gParse.ResourceFile("txtxml/jpGitLog.txt");
            gParse.main();
            Fuser myFuser = new Fuser();
            myFuser.main();
            WriteXMLFile.main();
            Visual.main(argv);
            
          
            //for (int k = 0; k < )
            //test print to check if info stored in User class is correct
            /*for (int j = 0; j< GitParser.userCount; j++){
         	   
         	    System.out.println("This is " + gParse.user[j].name);
                System.out.println(gParse.user[j].userNum);
                System.out.println(gParse.user[j].fileCount);
                for (int i = 0; i < gParse.user[j].fileCount; i++){

                    System.out.println(gParse.user[j].fileArray[i]);
                }
         	 }*/
            
            
    	}else{
    		
    		ReadXMLFile.main("JOD");
    		CreateFileClass.main();
    		
    		//count lines for each Files entry
    		CountLinesFromFile lineCounter = new CountLinesFromFile();
    		for (int k=0; k<CreateFileClass.fileCount; k++){
    			fileName = CreateFileClass.myFiles[k].name;
    			//System.out.println(fileName);
    			slashFileName = fileName.replace(".", "/");
    			//System.out.println(slashFileName);
    			fileLoc = "jodconverter/jodconverter-core/src/" + slashFileName + ".java";
    			//System.out.println(fileLoc);
                lineCounter.ResourceFile(fileLoc);
                CreateFileClass.myFiles[k].numLines =lineCounter.main();
      		}
    		
    		
    		ReadBugXMLFile.main("JOD");
    		GitParser gParse = new GitParser();
            gParse.ResourceFile("txtxml/jodlog.txt");
            gParse.main();
            Fuser myFuser = new Fuser();
            myFuser.main();
            WriteXMLFile.main();
            Visual.main(argv);
    	}
           
    }

}
