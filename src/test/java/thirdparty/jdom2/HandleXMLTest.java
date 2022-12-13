package thirdparty.jdom2;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author fixalot
 * @since 2017-07-27
 */
public class HandleXMLTest {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(HandleXMLTest.class);

    //	@Test
    public void merge() throws SAXException, IOException, ParserConfigurationException {
        File backweb = Paths.get("C:\\project\\workspace\\etbs\\backweb\\target\\pmd.xml").toFile();
        File batch = Paths.get("C:\\project\\workspace\\etbs\\batch\\target\\pmd.xml").toFile();
        File cardrelay = Paths.get("C:\\project\\workspace\\etbs\\cardrelay\\target\\pmd.xml").toFile();
        File common = Paths.get("C:\\project\\workspace\\etbs\\common\\target\\pmd.xml").toFile();
        File frontweb = Paths.get("C:\\project\\workspace\\etbs\\frontweb\\target\\pmd.xml").toFile();
        File welfare = Paths.get("C:\\project\\workspace\\etbs\\welfare\\target\\pmd.xml").toFile();
        assertTrue(backweb.exists());
        assertTrue(batch.exists());
        assertTrue(cardrelay.exists());
        assertTrue(common.exists());
        assertTrue(frontweb.exists());
        assertTrue(welfare.exists());

        ArrayList<File> files = new ArrayList<>();
        files.add(backweb);
        files.add(batch);
        files.add(cardrelay);
        files.add(common);
        files.add(frontweb);
        files.add(welfare);

        ArrayList<Element> fileTagList = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            // we can create JDOM Document from DOM, SAX and STAX Parser Builder classes

            // creating DOM Document
            DocumentBuilderFactory innerDbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder innerDBuilder = innerDbFactory.newDocumentBuilder();
            org.w3c.dom.Document innerDoc = innerDBuilder.parse(file);
            DOMBuilder innerDomBuilder = new DOMBuilder();
            Document innerJdomDoc = innerDomBuilder.build(innerDoc);

            Element innerRoot = innerJdomDoc.getRootElement();
            List<Element> childrenList = innerRoot.getChildren("file");
            for (Element ele : childrenList) {
                assertEquals("file", ele.getName());
                Element eleCopy = ele.clone();
                eleCopy.detach(); // JDOM2에서 DOM요소는 다른데로 옮기기 전에 detach 해야함.
                fileTagList.add(eleCopy);
            }
        }

        Document doc = new Document();
        doc.setRootElement(new Element("pmd"));

        for (Element ele : fileTagList) {
            assertEquals("file", ele.getName());
            doc.getRootElement().addContent(ele);
        }
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());

        File target = Paths.get("src\\test\\resources\\handle-xml-test\\mergeresult.xml").toFile();
        if (target.exists()) {
            target.delete();
        }

        xmlOutputter.output(doc, new FileOutputStream(target));
    }
}
