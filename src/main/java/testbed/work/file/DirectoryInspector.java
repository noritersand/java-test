package testbed.work.file;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public enum DirectoryInspector {
    ;

    public static void main(String[] args) {
        File dir = new File("C:/dev/temp");
        inspect(dir);

        log.debug("크기: {} 바이트", DirectoryInspector.sizeSum);
        log.debug("파일: {} 개", DirectoryInspector.fileCnt);
        log.debug("폴더: {} 개", DirectoryInspector.directoryCnt);
    }

    static int fileCnt;
    static int directoryCnt;
    static long sizeSum;

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
