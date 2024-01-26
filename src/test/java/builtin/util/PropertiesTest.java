package builtin.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class PropertiesTest {

    @Test
    void testStore() throws IOException {
        // 상대경로를 지정하면 루트는 '워크스페이스/프로젝트' 폴더다.
        File dir = new File("temp");
        Path testProperties = new File("temp/test.properties").toPath();
        File file = new File(testProperties.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(testProperties.toString());
        Properties prop = new Properties();
        prop.put("서울의수도", "서울에수도가어디있어");
        prop.store(fos, "코멘트테스트");
        fos.close();

        Properties newProp = new Properties();
        newProp.load(new FileInputStream(testProperties.toString()));
        assertEquals("서울에수도가어디있어", newProp.getProperty("서울의수도"));
    }

    @Test
    void getPropertyDefault() {
        Properties prop = new Properties();
        assertEquals("hello-world", prop.getProperty("I'm not exist", "hello-world"));
    }

    @Test
    void getPropertyByInputStream() throws IOException {
        FileInputStream fis = new FileInputStream("src\\test\\resources\\properties-test\\access-test.properties");
        Properties prop = new Properties();
        prop.load(fis);
        fis.close();
        assertTrue(prop.containsKey("a.b.c"));
        log.debug("{}", prop);

        assertEquals("http://daum.net", prop.getProperty("web.root"));
        assertEquals("123", prop.getProperty("a.b.c"));
        assertEquals("한글", prop.getProperty("korean"));
        assertEquals("한글2", prop.getProperty("korean2"));
    }

    @Test
    void getPropertyByReader() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src\\test\\resources\\properties-test\\access-test.properties", StandardCharsets.UTF_8));
        Properties prop = new Properties();
        prop.load(reader);
        reader.close();
        assertTrue(prop.containsKey("a.b.c"));
        log.debug("{}", prop);

        assertEquals("http://daum.net", prop.getProperty("web.root"));
        assertEquals("123", prop.getProperty("a.b.c"));
        assertEquals("한글", prop.getProperty("korean"));
        assertEquals("한글2", prop.getProperty("korean2"));
    }

    @Test
    void getPropertyFromXml() throws IOException {
        FileInputStream fis = new FileInputStream("src\\test\\resources\\properties-test\\uri-test.xml");
        Properties prop = new Properties();
        prop.loadFromXML(fis);
        fis.close();
        assertTrue(prop.containsKey("image.root"));
        log.debug("{}", prop);

        Iterator<Object> iterator = prop.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            log.debug("key: {}, value: {}", key, prop.getProperty(key));
        }

        // key: some.korean, value: 한글
        // key: image.root, value: http://tistory.com

        assertEquals("http://tistory.com", prop.getProperty("image.root"));
        assertEquals("한글", prop.getProperty("some.korean"));
    }
}
