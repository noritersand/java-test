package thirdparty.typesafe;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Lightbend의 com.typesafe.config.Config 테스트
 *
 * @author fixalot
 * @since 2019-07-19
 */
@Slf4j
class TypeSafeConfigTest {

    @Test
    void testConvertToHierarchicalJsonString() throws IOException {
        FileInputStream fis = new FileInputStream("src\\test\\resources\\properties-test\\convert-test.properties");
        Properties prop = new Properties();
        prop.load(fis);
        com.typesafe.config.Config config = com.typesafe.config.ConfigFactory.parseProperties(prop);
        String result = config.root().render(com.typesafe.config.ConfigRenderOptions.concise());
        assertEquals("{\"field1\":\"someValue2\",\"field2\":\"someValue3\",\"insurance\":{\"cost\":\"123\",\"type\":\"Medical\"},\"man\":{\"address\":{\"city\":\"Waraw\",\"street\":\"Jp2\"},\"emails\":\"example@gg.com ,example2@cc.com, example3@gg.com,example3@gg.com\",\"groups[0]\":{\"name\":\"group1\",\"type\":\"Commercial\"},\"groups[1]\":{\"name\":\"group2\",\"type\":\"Free\"},\"groups[2]\":{\"name\":\"group3\",\"type\":\"Commercial\"},\"hoobies[0]\":\"cars\",\"hoobies[1]\":\"science\",\"hoobies[2]\":\"women\",\"hoobies[3]\":\"computers\",\"insurance\":{\"cost\":\"126.543\"},\"name\":\"John\",\"surname\":\"Surname\"}}",
                result);
    }
}
