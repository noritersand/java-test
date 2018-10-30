package laboratory.work.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilePicker {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(FilePicker.class);

	private static final String targetLocation = "c:/dev/temp";
    private static final String destLocation = "c:/dev/temp2";
    private static final String[] fileNames = {
		"a", "b"
    };

    public static void main(String[] args) throws Exception {
        Path targetPath = new File(targetLocation).toPath();
        Stream<Path> targetList = Files.list(targetPath);

        // list.forEach(System.out::println);
        
        List<String> fileNameList = new ArrayList<String>(Arrays.asList(fileNames));

        targetList.forEach((ele) -> {
            final String fileName = ele.getFileName().toString();
            if (fileNameList.contains(fileName)) {
            	Path newPath = new File(destLocation, fileName).toPath();
            	try {
            		Files.copy(ele, newPath, StandardCopyOption.REPLACE_EXISTING);
            	} catch (IOException e) {
            		System.err.println(e);
            		System.exit(0);
            	}
            }
        });

        targetList.close();
    }
}
