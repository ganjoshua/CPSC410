
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 
public class WriteXMLFile {
	

	public static void main(String argv[]) {
		
	  try {
		  List<String[]> csvFile;
		  
		  FileReader csvFilePath = new FileReader("C:\\githubREPO\\CPSC410\\classParser\\test.txt");
		  csvFile = convertStreamToStringArray(csvFilePath);
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		// root elements graphml
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("graphml");
		doc.appendChild(rootElement);
		Attr attr = doc.createAttribute("xmlns");
		attr.setTextContent("http://graphml.graphdrawing.org/xmlns");
		rootElement.setAttributeNode(attr);

		// graph element
		Element graph = doc.createElement("graph");
		rootElement.appendChild(graph);
 
		// set attribute to graph element
		attr = doc.createAttribute("edgedefault");
		attr.setTextContent("undirected");
		graph.setAttributeNode(attr);
		
		//data schema --> 
		//<key id="name" for="node" attr.name="name" attr.type="string"/>
		Element key = doc.createElement("key");
		graph.appendChild(key);
		key.setAttribute("id","name");
		key.setAttribute("for","node");
		key.setAttribute("attr.name","name");
		key.setAttribute("attr.type","string");
		//<key id="gender" for="node" attr.name="pbugs" attr.type="string"/>
		key = doc.createElement("key");
		graph.appendChild(key);
		key.setAttribute("id","gender");
		key.setAttribute("for","node");
		key.setAttribute("attr.name","pbugs");
		key.setAttribute("attr.type","string");
		
		//write nodes
		for(int i = 0; i < csvFile.size();i++){
			Element node = doc.createElement("node");
			graph.appendChild(node);
			node.setAttribute("id", csvFile.get(i)[0]);
			Element data = doc.createElement("data");
			node.appendChild(data);
			data.setAttribute("key", "name");
			data.appendChild(doc.createTextNode(csvFile.get(i)[0]));
			data = doc.createElement("data");
			node.appendChild(data);
			data.setAttribute("key", "pBugs");
			data.appendChild(doc.createTextNode(i+""));
		}
		
		//write edges <-- which file is linked to the source
		for(int i = 0; i < csvFile.size();i++){
			for(int j = 1; j <csvFile.get(i).length;j++){
				Element edge = doc.createElement("edge");
				graph.appendChild(edge);
				edge.setAttribute("source", csvFile.get(i)[0]);
				edge.setAttribute("target", csvFile.get(i)[j]);
			}
		}
		
 
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("C:\\githubREPO\\CPSC410\\createXML\\file.xml"));
	
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		transformer.transform(source, result);
		
		System.out.println("File saved!");
 
	  } catch (Exception pce) {
		pce.printStackTrace();
	  }
	}
	
	
	
	
	
	
	public static List<String[]> convertStreamToStringArray(FileReader is) throws Exception {
        BufferedReader reader = new BufferedReader(is);
        String line = null;
        List<String[]> csvFile = new ArrayList<String[]>();

        while ((line = reader.readLine()) != null) {
        	String[] csvLine;
        	//csvLine = line.split(": ");
			csvLine = line.split(", ");
			
			csvFile.add(csvLine);
        }
        reader.close();
        return csvFile;
    }

}