import javax.xml.parsers.*;

import org.xml.sax.SAXException;
import org.w3c.dom.*;

import java.io.*;

/**
 * 
 * @author Nada Al-Thawr This class is to practice catch and try, parsing and
 *         reading XML files
 *
 */
public class FounderFileReader {
	/**
	 * main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Setup XML Document
		// We were getting exceptions so we put the code in the try block to be
		// able to run the code
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();
			// create a new xml file
			File xmlFile = new File(args[0]);
			Document document = builder.parse(xmlFile);
			// we call our method and pass in document as an argument
			parseFounderFile(document);
			// then we catch all the exceptions
		} catch (ParserConfigurationException e) {
			System.err.println("ParserConfigurationException: "
					+ e.getMessage());
		} catch (SAXException e) {
			System.err.println("SAXException: " + e.getMessage());

		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());

		}
	}

	/**
	 * method that navigates through the document structure
	 * 
	 * @param document
	 */
	private static void parseFounderFile(Document document) {
		// create a new node and get the elements in the document
		Node docRoot = document.getDocumentElement();
		// call parse node on the docRoot
		parseNode(docRoot);
	}

	/**
	 * recursive method, is called on all the nodes in the document
	 * 
	 * @param n
	 */
	private static void parseNode(Node n) {
		// if the node type equals the element nodes
		if (n.getNodeType() == Node.ELEMENT_NODE) {
			// set the element n as current element
			Element currentElt = (Element) n;
			// if the current element has attribute, which is the id
			if (currentElt.hasAttribute("id")) {
				// get the id and print it
				currentElt.getAttribute("id");
				System.out.println("id: " + currentElt.getAttribute("id"));
			}
			// if the nodename in currenrElt equals "name","year" or "college"
			if (currentElt.getNodeName().equals("name")
					|| currentElt.getNodeName().equals("year")
					|| currentElt.getNodeName().equals("college")) {
				// print the name with the content
				System.out.println(currentElt.getNodeName() + ": "
						+ currentElt.getTextContent());
			}
			// if the node has childnodes
			if (n.hasChildNodes()) {
				// create a list that has the child node
				NodeList list = n.getChildNodes();
				// loop through the list
				for (int i = 0; i < list.getLength(); i++) {
					// recursively call parse node on i
					parseNode(list.item(i));
				}

			}
		}
	}
}