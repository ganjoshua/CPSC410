package classParser;
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
 
	File fXmlFile = new File("C:/githubREPO/CPSC410/Analysis_Result/ClassycleAnalyse.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
	 //System.out.print(doc);
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();
 
	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
 
	NodeList nList = doc.getElementsByTagName("classes");
	//Node nNode = nList.item(1);
	
	//System.out.println("----------------------------");
 
	for (int temp = 1; temp < nList.getLength(); temp++) {
 
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
						System.out.println(temp1 + " Class Name : " + eElement1.getAttribute("name"));
						
						NodeList oList = eElement1.getElementsByTagName("classRef");
						for(int temp2 = 0; temp2 < oList.getLength(); temp2++){
							Node oNode = oList.item(temp2);
							if (oNode.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement2 = (Element) oNode;
								System.out.println("Link to : " + eElement2.getAttribute("name"));
							}
						}
					}
					System.out.println("");
				}
		}
	}
    } catch (Exception e) {
    	e.printStackTrace();
    }
  }
}