package laboratory.forblog;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotLowercaseFilenameFinder {
	private static final Logger logger = LoggerFactory.getLogger(NotLowercaseFilenameFinder.class);

	private static final String targetLocation = "C:\\dev\\git\\noritersand.github.io\\_drafts";
	private static final String targetLocation2 = "C:\\dev\\git\\noritersand.github.io\\_posts";

    public static void main(String[] args) throws Exception {
        find(targetLocation);
        find(targetLocation2);
    }
    
    private static void find(String path) throws IOException {
    	Path targetPath = new File(path).toPath();
        Stream<Path> targetList = Files.list(targetPath);
        
        targetList.forEach((ele) -> {
            final String fileName = ele.getFileName().toString();
            
            if (!fileName.equals(fileName.toLowerCase())) {
            	logger.debug(fileName);
            }
        });

        targetList.close();
    }
}
