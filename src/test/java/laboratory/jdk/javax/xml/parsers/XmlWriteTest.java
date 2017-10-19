package laboratory.jdk.javax.xml.parsers;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * <a href=
 * "http://www.journaldev.com/1112/how-to-write-xml-file-in-java-dom-parser">http://www.journaldev.com/1112/how-to-write-xml-file-in-java-dom-parser</a>
 * 
 * @since 2017-05-25
 * @author fixalot
 */
public class XmlWriteTest {
	private static final Logger logger = LoggerFactory.getLogger(XmlWriteTest.class);

	@Test
	public void writeXml() throws ParserConfigurationException, TransformerException {
		// TODO <!DOCTYPE xml> 추가해야 경고 안뜸

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		// add elements to Document
		Element rootElement = doc.createElementNS("http://www.journaldev.com/employee", "Employees");
		// append root element to document
		doc.appendChild(rootElement);
		// append first child element to root element
		rootElement.appendChild(getEmployee(doc, "1", "Pankaj", "29", "Java Developer", "Male"));

		// append second child
		rootElement.appendChild(getEmployee(doc, "2", "Lisa", "35", "Manager", "Female"));

		// for output to file, console
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		// for pretty print
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(doc);

		// write to console or file
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(new File("src/test/resources/xml-write-test/writeonme.xml"));

		// write data
		transformer.transform(source, console);
		transformer.transform(source, file);

		logger.debug("done");
	}

	private Node getEmployee(Document doc, String id, String name, String age, String role, String gender) {
		Element employee = doc.createElement("Employee");

		// set id attribute
		employee.setAttribute("id", id);

		// create name element
		employee.appendChild(getEmployeeElements(doc, employee, "name", name));

		// create age element
		employee.appendChild(getEmployeeElements(doc, employee, "age", age));

		// create role element
		employee.appendChild(getEmployeeElements(doc, employee, "role", role));

		// create gender element
		employee.appendChild(getEmployeeElements(doc, employee, "gender", gender));

		return employee;
	}

	// utility method to create text node
	private Node getEmployeeElements(Document doc, Element element, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
}
