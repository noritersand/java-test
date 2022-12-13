package jdk.javax.xml.parsers;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author fixalot
 * @since 2017-07-27
 */
public class DocumentBuilderTest {
    // @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(DocumentBuilderTest.class);

    @Test
    public void readXml() throws ParserConfigurationException, SAXException, IOException {
        File file = new File("src/test/resources/document-builder-test/try-read-this.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        Element rootElement = document.getDocumentElement();
        NodeList nameTags = rootElement.getElementsByTagName("first-child-tag");
        Node nameTag = nameTags.item(0);
        logger.debug("tag name: " + nameTag.getNodeName());
        logger.debug("tag text: " + nameTag.getFirstChild().getNodeValue());
    }
}
