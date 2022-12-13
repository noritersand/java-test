package lab.work.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * FILE_NAMES에 해당하는 파일 찾아서... 뭐하는거지?
 *
 * @author noritersand
 * @since 2020-01-29
 */
public class FilePicker {
    private static final Logger logger = LoggerFactory.getLogger(FilePicker.class);

    private static final String TARGET_LOCATION = "c:/dev/temp";
    private static final String DEST_LOCATION = "c:/dev/temp2";
    private static final String[] FILE_NAMES = {"a", "b"};

    public static void main(String[] args) {
        final File destDirectory = new File(DEST_LOCATION);
        if (!destDirectory.exists()) {
            destDirectory.mkdirs();
        }
        Path targetPath = new File(TARGET_LOCATION).toPath();
        try (Stream<Path> targetList = Files.list(targetPath)) {
//			list.forEach(System.out::println);

            List<String> fileNameList = new ArrayList<>(Arrays.asList(FILE_NAMES));

            targetList.forEach((Path ele) -> {
                final String fileName = ele.getFileName().toString();
                if (fileNameList.contains(fileName)) {
                    Path newPath = new File(DEST_LOCATION, fileName).toPath();
                    try {
                        Files.copy(ele, newPath, StandardCopyOption.REPLACE_EXISTING);
                        logger.debug("file copied: {} => {}", ele, newPath);
                    } catch (IOException e) {
                        logger.error(e.getMessage(), e);
                        System.exit(1);
                    }
                }
            });

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            System.exit(1);
        }

    }
}
