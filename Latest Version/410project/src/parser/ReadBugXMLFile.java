/* Code modified based on the one from here:
 * http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
 */

package parser;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import code.CreateFileClass;

import java.io.File;
 
public class ReadBugXMLFile {
 
  public static void main(String mode) {
	  File fXmlFile;
	  
    try {
 
    if (mode.equals("JPacman")){
    	fXmlFile = new File("txtxml/jpacman-undo.fbwarnings.xml");
    }else if (mode.equals("JOD")){
    	fXmlFile = new File("txtxml/jod_bugfinder.xml");
    }else{
    	System.out.println("Error opening findbugs XML file");
    	return;
    }
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
 
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();
 
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
 
	NodeList nList = doc.getElementsByTagName("ClassStats");
 
	System.out.println("----------------------------");
 
	for (int i = 0; i < nList.getLength(); ++i)
	{

		Element labTest = (Element) nList.item(i);
	    String _class = labTest.getAttribute("class");
	    int nbugs = Integer.valueOf(labTest.getAttribute("bugs"));
	    System.out.println("Rank is: " + _class);
	    System.out.println("number of bugs is "+ nbugs);
	    
	    for (int k=0; k<CreateFileClass.fileCount; k++){
        	if (CreateFileClass.myFiles[k].name.equals(_class)){
        		CreateFileClass.myFiles[k].bugRank = nbugs;
        		//System.out.println("Files entry found");
        	}else{
        		//System.out.println("No Files entry found");
        	}
        }
	    
	    /*NodeList ClassList = labTest.getElementsByTagName("Class");
	    for (int j = 0; j < ClassList.getLength(); ++j)
	    {
	        Element value = (Element) ClassList.item(j);
	        String _classname = value.getAttribute("classname");
	        
	        //System.out.println("Class name is: " + _classname);
	        //System.out.println(" ");
	        
	    }*/
	}
 
		} catch (Exception e) {
	e.printStackTrace();
    }
  }
 
}