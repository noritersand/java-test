package lab.work.file;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

/**
 * 특정 폴더의 모든 파일 이름 변경
 *
 * @author noritersand
 * @since 2020-01-29
 */
@Slf4j
public enum FileNameReplacer {
    ;

    private static final String TARGET_LOCATION = "C:\\Users\\norit\\Downloads\\wallpaper";
    private static final String DEST_LOCATION = "C:\\Users\\norit\\Downloads\\wallpaper\\temp";
    private static final String REMOVE_ME = "wallpaper-warhammer40k-";
    private static final String PREFIX;

    static {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String yyyyMmdd = today.format(formatter);

        PREFIX = "wallpaper" + "-" + yyyyMmdd + "-";
    }
//	private static final String suffix = "";

    public static void main(String[] args) {
        Path path = new File(TARGET_LOCATION).toPath();
        try (Stream<Path> list = Files.list(path)) {
            // list.forEach(System.out::println);

            File newPathParent = new File(DEST_LOCATION);
            if (!newPathParent.exists()) {
                newPathParent.mkdirs();
            }

            addPrefix(list);
//			removePrefix(list);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            System.exit(1);
        }
    }

    @SuppressWarnings("unused")
    private static void removePrefix(Stream<Path> list) {
        list.forEach(k -> {
            if (!Files.isDirectory(k)) {
                String fileName = k.toString();
                String newFileName = (fileName.substring(fileName.lastIndexOf(File.separator) + 1)).replaceFirst(REMOVE_ME, "");
                Path newPath = new File(DEST_LOCATION, newFileName).toPath();

                // logging
                log.debug("{} -> {}", k, newPath);

                try {
                    Files.copy(k, newPath, StandardCopyOption.REPLACE_EXISTING);
//					Files.move(k, newPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
        });
    }

    private static void addPrefix(Stream<Path> list) {
        list.forEach(k -> {
            if (!Files.isDirectory(k)) {
                String fileName = k.toString();
                String newFileName = PREFIX + fileName.substring(fileName.lastIndexOf(File.separator) + 1);
                Path newPath = new File(DEST_LOCATION, newFileName).toPath();

                // logging
                log.debug("{} -> {}", k, newPath);

                try {
                    Files.copy(k, newPath, StandardCopyOption.REPLACE_EXISTING);
//					Files.move(k, newPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
        });
    }
}
