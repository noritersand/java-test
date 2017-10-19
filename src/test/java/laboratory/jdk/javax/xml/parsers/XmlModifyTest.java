package laboratory.jdk.javax.xml.parsers;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * <a href=
 * "http://www.journaldev.com/901/modify-xml-file-in-java-dom-parser">http://www.journaldev.com/901/modify-xml-file-in-java-dom-parser</a>
 * 
 * @since 2017-05-25
 * @author fixalot
 */
public class XmlModifyTest {
	private static final Logger logger = LoggerFactory.getLogger(XmlModifyTest.class);

//	@Test
	public void modifyXml() throws TransformerException, ParserConfigurationException, SAXException, IOException {
		String filePath = "src/test/resources/xml-modify-test/original.xml";
		File xmlFile = new File(filePath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		
		// FIXME <!DOCTYPE xml> 추가해야 경고 안떠서 추가한 코드가 아래부분인데... 쓰여지는 값이 이상함
//		DocumentType doctype = doc.getImplementation().createDocumentType("doctype", "FIXME", "FIXME");
		
		doc.getDocumentElement().normalize();
		
		// update attribute value
		updateAttributeValue(doc);

		// update Element value
		updateElementValue(doc);

		// delete element
		deleteElement(doc);

		// add new element
		addElement(doc);

		// write the updated document to file or console
		doc.getDocumentElement().normalize();
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("src/test/resources/xml-modify-test/result.xml"));
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//		transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
//		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
		transformer.transform(source, result);
		logger.debug("XML file updated successfully");
	}

	private static void addElement(Document doc) {
		NodeList employees = doc.getElementsByTagName("Employee");
		Element emp = null;

		// loop for each employee
		for (int i = 0; i < employees.getLength(); i++) {
			emp = (Element) employees.item(i);
			Element salaryElement = doc.createElement("salary");
			salaryElement.appendChild(doc.createTextNode("10000"));
			emp.appendChild(salaryElement);
		}
	}

	private static void deleteElement(Document doc) {
		NodeList employees = doc.getElementsByTagName("Employee");
		Element emp = null;
		// loop for each employee
		for (int i = 0; i < employees.getLength(); i++) {
			emp = (Element) employees.item(i);
			Node genderNode = emp.getElementsByTagName("gender").item(0);
			emp.removeChild(genderNode);
		}

	}

	private static void updateElementValue(Document doc) {
		NodeList employees = doc.getElementsByTagName("Employee");
		Element emp = null;
		// loop for each employee
		for (int i = 0; i < employees.getLength(); i++) {
			emp = (Element) employees.item(i);
			Node name = emp.getElementsByTagName("name").item(0).getFirstChild();
			name.setNodeValue(name.getNodeValue().toUpperCase());
		}
	}

	private static void updateAttributeValue(Document doc) {
		NodeList employees = doc.getElementsByTagName("Employee");
		Element emp = null;
		// loop for each employee
		for (int i = 0; i < employees.getLength(); i++) {
			emp = (Element) employees.item(i);
			String gender = emp.getElementsByTagName("gender").item(0).getFirstChild().getNodeValue();
			if (gender.equalsIgnoreCase("male")) {
				// prefix id attribute with M
				emp.setAttribute("id", "M" + emp.getAttribute("id"));
			} else {
				// prefix id attribute with F
				emp.setAttribute("id", "F" + emp.getAttribute("id"));
			}
		}
	}
}
