package info;

public class Files {

    public String name;

    //used to store a list of the user number of the contributors
    public int[] contributors = new int[20];
    public int numContributors;
    public int numChanges;
    public int numLines;
	
	//TO-DO: link this to BugsFinder Parser
	//value given to bugs finder analysis
	public int bugRank;
	
	public int pBugs;
	public int normPBugs;
	public String nPBval;

}
