/**
 * Created by Joshua on 25/10/2014.
 */
public class Files {

    String name;

    //used to store a list of the user number of the contributors
    int[] contributors = new int[20];
    int numChanges;
    int numLines;
	
	//Store names of files it is linked to
	//Considerations: perhaps used a file index number instead? 
	//TO-DO: link this to ClassParser
	String[] dependencies = new String[20];
	
	//TO-DO: link this to BugsFinder Parser
	//value given to bugs finder analysis
	int bugRank;

}
