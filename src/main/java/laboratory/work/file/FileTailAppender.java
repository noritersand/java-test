package laboratory.work.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.LinkedList;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 하나 이상의 파일들 내용 끝에 빈 칸 하나 집어넣는 클래스.<br>
 * svn이나 git에서 여러 파일을 하나의 커밋 아이디로 묶을 때 사용한다.<br>
 * 사실 정상적인 시스템이면 이런 게 필요할 리가 없다.
 * 
 * @since 2020-01-29
 * @author noritersand
 */
public class FileTailAppender {
	private static final Logger logger = LoggerFactory.getLogger(FileTailAppender.class);

	/**
	 * '대상 파일의 경로를 적어놓은 파일'의 경로. 라인으로 각 파일명을 구분한다.
	 */
	private static final String TARGET_LOCATION_FILE = "C:/dev/test/locations.txt";

	public static void main(String[] args) {
		File locationFile = new File(TARGET_LOCATION_FILE);
		if (!locationFile.exists() || !locationFile.isFile()) {
			logger.error("TARGET_LOCATION_FILE이 없거나 파일이 아님.");
			System.exit(1);
		}
		try (BufferedReader reader = new BufferedReader(new FileReader(locationFile))) {
			String singleLine = null;
			LinkedList<String> targetList = new LinkedList<>();
			StringBuilder txt = new StringBuilder();

			while ((singleLine = reader.readLine()) != null) { // 읽을게 없으면 null 리턴
				txt.append(singleLine).append(System.lineSeparator());
				if (StringUtils.isBlank(singleLine) || isCommentLine(singleLine)) {
					continue;
				}
				targetList.add(singleLine);
			}
			logger.debug("TARGET_LOCATION_FILE 내용:{}{}", System.lineSeparator(), txt);
			if (targetList.isEmpty()) {
				System.exit(1);
			}
			for (String targetLocation : targetList) {
				appendBlank(targetLocation);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			System.exit(1);
		}
	}

	private static boolean isCommentLine(String singleLine) {
		return singleLine.indexOf('#') == 0;
	}

	private static void appendBlank(String targetLocation) throws IOException {
		File target = new File(targetLocation);
		if (!target.exists() || !target.isFile()) {
			logger.warn("대상 파일이 없음: {}", target);
			return;
		}
		try (Writer output = new BufferedWriter(new FileWriterWithEncoding(target, Charset.forName("utf-8"), true))) {
			output.append(" ");
//			output.append(System.lineSeparator()); // 이렇게 하면 줄 낭비가...
			logger.debug("{} 파일에 빈 칸 추가", target);
		}
	}
}
