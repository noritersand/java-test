package lab.work.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * JSON을 뭔가로 바꿈.
 *
 * @author fixalot
 * @since 2018-10-31
 */
public class JsonConverter {
    private static final Logger logger = LoggerFactory.getLogger(JsonConverter.class);

    private static final File jsonFileLocation = new File("c:/dev/temp/convert-me.json");
    private static final File destinationParent = new File("c:/dev/temp2");

    public static void main(String[] args) {

        if (!jsonFileLocation.exists()) {
            logger.error("파일이 없어요.");
            System.exit(1);
        }

        if (!destinationParent.exists()) {
            destinationParent.mkdirs();
        }

        convertToCsv();
    }

    public static void convertToCsv() {
        // TODO complete me
    }
}
