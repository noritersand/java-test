package laboratory.test.jdom2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

public class HandleXMLTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(HandleXMLTest.class);
	
	@Test
	public void testMerge() throws SAXException, IOException, ParserConfigurationException {
		File target = Paths.get("src\\test\\resources\\xml\\merge-test.xml").toFile();
		if (target.exists()) {
			target.delete();
		}
		
		File backweb = Paths.get("C:\\project\\workspace\\etbs\\backweb\\target\\pmd.xml").toFile();
		File batch = Paths.get("C:\\project\\workspace\\etbs\\batch\\target\\pmd.xml").toFile();
		File cardrelay = Paths.get("C:\\project\\workspace\\etbs\\cardrelay\\target\\pmd.xml").toFile();
		File common = Paths.get("C:\\project\\workspace\\etbs\\common\\target\\pmd.xml").toFile();
		File frontweb = Paths.get("C:\\project\\workspace\\etbs\\frontweb\\target\\pmd.xml").toFile();
		File welfare = Paths.get("C:\\project\\workspace\\etbs\\welfare\\target\\pmd.xml").toFile();
		Assert.assertTrue(backweb.exists());
		Assert.assertTrue(batch.exists());
		Assert.assertTrue(cardrelay.exists());
		Assert.assertTrue(common.exists());
		Assert.assertTrue(frontweb.exists());
		Assert.assertTrue(welfare.exists());

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
			
	        //creating DOM Document
	        DocumentBuilderFactory innerDbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder innerDBuilder = innerDbFactory.newDocumentBuilder();
	        org.w3c.dom.Document innerDoc = innerDBuilder.parse(file);
	        DOMBuilder innerDomBuilder = new DOMBuilder();
	        Document innerJdomDoc = innerDomBuilder.build(innerDoc);
			
			Element innerRoot = innerJdomDoc.getRootElement();
			List<Element> childrenList = innerRoot.getChildren("file");
			for (Element ele : childrenList) {
				Assert.assertEquals("file", ele.getName());
				Element eleCopy = (Element) ele.clone();
				eleCopy.detach(); // JDOM2에서 DOM요소는 다른데로 옮기기 전에 detach 해야함.
				fileTagList.add(eleCopy);
			}
		}			
        
        Document doc = new Document();
        doc.setRootElement(new Element("pmd"));
        
        for (Element ele : fileTagList) {
        	Assert.assertEquals("file", ele.getName());
    		doc.getRootElement().addContent(ele);        		
        }
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(doc, new FileOutputStream(target));
	}
}
