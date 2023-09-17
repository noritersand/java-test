package testbed.work.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

/**
 * 하나 이상의 파일들 내용 끝에 빈 칸 하나 집어넣는 클래스.<br>
 * 버전 관리 시 여러 파일을 하나의 커밋으로 묶을 때 사용한다.<br>
 * 사실 정상적인 시스템이면 이런 게 필요할 리가 없다.
 *
 * @author noritersand
 * @since 2020-01-29
 */
@Slf4j
public enum FileTailAppender {
    ;

    /**
     * '대상 파일의 경로를 적어놓은 파일'의 경로. 라인으로 각 파일명을 구분한다.
     */
    private static final String TARGET_LOCATION_FILE = "C:/dev/test/locations.txt";

    public static void main(String[] args) {
        File locationFile = new File(TARGET_LOCATION_FILE);
        if (!locationFile.exists() || !locationFile.isFile()) {
            log.error("TARGET_LOCATION_FILE이 없거나 파일이 아님.");
            System.exit(1);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(locationFile, StandardCharsets.UTF_8))) {
            String singleLine = null;
            LinkedList<String> targetList = new LinkedList<>();
            StringBuilder txt = new StringBuilder();

            while (null != (singleLine = reader.readLine())) { // 읽을게 없으면 null 리턴
                txt.append(singleLine).append(System.lineSeparator());
                if (StringUtils.isBlank(singleLine) || isCommentLine(singleLine)) {
                    continue;
                }
                targetList.add(singleLine);
            }
            log.debug("TARGET_LOCATION_FILE 내용:{}{}", System.lineSeparator(), txt);
            if (targetList.isEmpty()) {
                log.info("대상 없음. 메서드 종료.");
                System.exit(1);
            }
            for (String targetLocation : targetList) {
                appendBlank(targetLocation);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            System.exit(1);
        }
    }

    /**
     * trim 후 맨 좌측 첫 번째 문자가 #이면 코멘트로 판단.
     *
     * @param singleLine 코멘트인지 판단할 한 줄
     * @return 코멘트 라인이면 true, 아니면 false
     * @author noritersand
     */
    private static boolean isCommentLine(String singleLine) {
        return 0 == singleLine.trim().indexOf('#');
    }

    /**
     * 파일에 공백문자 하나 추가.
     *
     * @param targetLocation 대상 파일의 경로
     * @throws IOException
     * @author noritersand
     */
    private static void appendBlank(String targetLocation) throws IOException {
        File target = new File(targetLocation);
        if (!target.exists() || !target.isFile()) {
            log.warn("대상 파일이 없음: {}", target);
            return;
        }
        try (Writer output = new BufferedWriter(new FileWriterWithEncoding(target, StandardCharsets.UTF_8, true))) {
            output.append(" ");
//			output.append(System.lineSeparator()); // 이렇게 하면 줄 낭비가...
            log.debug("{} 파일에 빈 칸 추가", target);
        }
    }
}
