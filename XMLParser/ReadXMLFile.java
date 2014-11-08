import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
 
public class ReadXMLFile {
 
  public static void main(String argv[]) {
 
    try {
 
	File fXmlFile = new File("/Users/Johnny/Desktop/jpacman-undo.fbwarnings.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
 
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();
 
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
 
	NodeList nList = doc.getElementsByTagName("BugInstance");
 
	System.out.println("----------------------------");
 
	for (int i = 0; i < nList.getLength(); ++i)
	{

		Element labTest = (Element) nList.item(i);
	    String _rank = labTest.getAttribute("rank");
	    System.out.println("Rank is: " + _rank);

	    NodeList ClassList = labTest.getElementsByTagName("Class");
	    for (int j = 0; j < ClassList.getLength(); ++j)
	    {
	        Element value = (Element) ClassList.item(j);
	        String _classname = value.getAttribute("classname");
	        System.out.println("Class name is: " + _classname);
	        System.out.println(" ");
	        
	    }
	}
 
		} catch (Exception e) {
	e.printStackTrace();
    }
  }
 
}