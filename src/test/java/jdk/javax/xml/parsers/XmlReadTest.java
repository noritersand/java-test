package jdk.javax.xml.parsers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <a href=
 * "http://www.journaldev.com/898/how-to-read-an-xml-file-in-java-dom-parser">http://www.journaldev.com/898/how-to-read-an-xml-file-in-java-dom-parser</a>
 *
 * @author fixalot
 * @since 2017-05-25
 */
@Slf4j
public class XmlReadTest {

    @Test
    public void readXml() throws Exception {
        String filePath = "src/test/resources/xml-read-test/readme.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        log.debug("Root element :{}", doc.getDocumentElement().getNodeName());

        NodeList nodeList = doc.getElementsByTagName("Employee");
        // now XML is loaded as Document in memory, lets convert it to Object List
        List<Employee> empList = new ArrayList<Employee>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            empList.add(getEmployee(nodeList.item(i)));
        }
        // lets print Employee list information
        for (Employee emp : empList) {
            log.debug(emp.toString());
        }
    }

    private Employee getEmployee(Node node) {
        // XMLReaderDOM domReader = new XMLReaderDOM();
        Employee emp = new Employee();
        if (Node.ELEMENT_NODE == node.getNodeType()) {
            Element element = (Element) node;
            emp.setName(getTagValue("name", element));
            emp.setAge(Integer.parseInt(getTagValue("age", element)));
            emp.setGender(getTagValue("gender", element));
            emp.setRole(getTagValue("role", element));
        }

        return emp;
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    @SuppressWarnings("unused")
    private class Employee {
        private String name;
        private String gender;
        private int age;
        private String role;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        @Override
        public String toString() {
            return "Employee:: Name=" + this.name + " Age=" + this.age + " Gender=" + this.gender + " Role=" + this.role;
        }
    }
}
