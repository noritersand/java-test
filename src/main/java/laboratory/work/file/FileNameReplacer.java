package laboratory.work.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileNameReplacer {
	private static final Logger logger = LoggerFactory.getLogger(FileNameReplacer.class);

	private static final String targetLocation = "C:\\Users\\norit\\Downloads\\wallpaper";
	private static final String destLocation = "C:\\Users\\norit\\Downloads\\wallpaper\\temp";
	private static final String removeMe = "wallpaper-warhammer40k-";
	private static final String prefix;
	static {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		final String yyyyMmdd = today.format(formatter);
		
		prefix = "wallpaper" + "-" + yyyyMmdd + "-";
	}
//	private static final String suffix = "";

	public static void main(String[] args) throws Exception {
		Path path = new File(targetLocation).toPath();
		Stream<Path> list = Files.list(path);

		// list.forEach(System.out::println);

		File newPathParent = new File(destLocation);
		if (!newPathParent.exists()) {
			newPathParent.mkdirs();
		}

		addPrefix(list);
//		removePrefix(list);

		list.close();
	}

	@SuppressWarnings("unused")
	private static void removePrefix(Stream<Path> list) {		
		list.forEach((k) -> {
			if (!Files.isDirectory(k)) {
				final String fileName = k.toString();
				final String newFileName = (fileName.substring(fileName.lastIndexOf(File.separator) + 1)).replaceFirst(removeMe, "");
				Path newPath = new File(destLocation, newFileName).toPath();

				// logging
				logger.debug("{} -> {}", k.toString(), newPath.toString());

				try {
					Files.copy(k, newPath, StandardCopyOption.REPLACE_EXISTING);
//					Files.move(k, newPath, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					System.err.println(e);
					System.exit(0);
				}
			}
		});
	}

	private static void addPrefix(Stream<Path> list) {
		list.forEach((k) -> {
			if (!Files.isDirectory(k)) {
				final String fileName = k.toString();
				final String newFileName = prefix + fileName.substring(fileName.lastIndexOf(File.separator) + 1);
				Path newPath = new File(destLocation, newFileName).toPath();

				// logging
				logger.debug("{} -> {}", k.toString(), newPath.toString());

				try {
					Files.copy(k, newPath, StandardCopyOption.REPLACE_EXISTING);
//					Files.move(k, newPath, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					System.err.println(e);
					System.exit(0);
				}	
			}
		});
	}
}