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

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class DocumentBuilderTest {

    @Test
    void readXml() throws Exception {
        File file = new File("src/test/resources/document-builder-test/try-read-this.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        Element rootElement = document.getDocumentElement();
        NodeList nameTags = rootElement.getElementsByTagName("first-child-tag");
        Node nameTag = nameTags.item(0);
        log.debug("tag name: {}", nameTag.getNodeName());
        log.debug("tag text: {}", nameTag.getFirstChild().getNodeValue());
    }
}
