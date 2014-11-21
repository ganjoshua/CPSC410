package classParser;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ReadXMLFile {
 
  public static void main(String argv[]) {
	  BufferedWriter writer = null;
    try {
    	//create a logfile and write the required information from the xml file into it
    	File logFile = new File("C:\\githubREPO\\CPSC410\\classParser\\test.txt");
    	writer = new BufferedWriter(new FileWriter(logFile));
    	
    	//create a filepath to the classcycleAnalyse.xml file
		File fXmlFile = new File("C:/githubREPO/CPSC410/Analysis_Result/ClassycleAnalyse.xml");
		
		//create a documentbuilder to read the xml file
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		
		//Find the elements named "classes" and read their attributes
		NodeList nList = doc.getElementsByTagName("classes");
		for(int temp = 1; temp < nList.getLength(); temp++) {
	 
			Node nNode = nList.item(temp);
			//System.out.println("\nCurrent Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				//System.out.println("numberOfExternalClasses : " + eElement.getAttribute("numberOfExternalClasses"));
				
				NodeList mList = eElement.getElementsByTagName("class");
				for(int temp1 = 0; temp1 < mList.getLength(); temp1++){
					Node mNode = mList.item(temp1);
					//System.out.println("\nCurrent Element :" + mNode.getNodeName());
					if (mNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement1 = (Element) mNode;
						//write class name into logfile
						System.out.println(temp1 + " Class Name : " + eElement1.getAttribute("name"));
						writer.write(eElement1.getAttribute("name") + ", ");
						
						NodeList oList = eElement1.getElementsByTagName("classRef");
						for(int temp2 = 0; temp2 < oList.getLength(); temp2++){
							Node oNode = oList.item(temp2);
							if (oNode.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement2 = (Element) oNode;
								
								//check if the attribute's type is "usesInternal"
								if(eElement2.getAttribute("type").equals("usesInternal")){
									//write name of class used by the class
									System.out.println("Link to : " + eElement2.getAttribute("name"));
									writer.write(eElement2.getAttribute("name") + ", ");
									
								}
							}
						}
						writer.newLine();
					}
					System.out.println("");
				}
			}
	}
    } catch (Exception e) {
    	e.printStackTrace();
    }finally{
    	try {
            // Close the writer regardless of what happens...
            writer.close();
        } catch (Exception e) {
        	System.out.println("Error, fail to close writer");
        }
    }
  }
}