package lab.work.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class DirectoryInspector {
    private static final Logger logger = LoggerFactory.getLogger(DirectoryInspector.class);

    public static void main(String[] args) {
        File dir = new File("C:/dev/temp");
        inspect(dir);

        logger.debug("크기: {} 바이트", sizeSum);
        logger.debug("파일: {} 개", fileCnt);
        logger.debug("폴더: {} 개", directoryCnt);
    }

    static int fileCnt = 0;
    static int directoryCnt = 0;
    static long sizeSum = 0;

    /**
     * static 클래스 변수에 값 누적
     *
     * @param dir
     * @author fixalot
     */
    private static void inspect(File dir) {
        File[] list = dir.listFiles();

        for (File ele : list) {
            if (ele.isDirectory()) { // 디렉토리면
                // 중단하고 발견한 폴더에서 재탐색
                inspect(ele);
                directoryCnt++;
            } else if (ele.isFile()) { // 파일이면
                // 현재경로의 파일 개수, 폴더 개수 합산
                fileCnt++;
                sizeSum += ele.length();
            }
        }
    }
}
