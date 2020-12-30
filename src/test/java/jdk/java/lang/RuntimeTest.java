package jdk.java.lang;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Runtime 클래스 테스트
 * 
 * @since 2018-12-11
 * @author fixalot
 */
public class RuntimeTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(RuntimeTest.class);

	/**
	 * JAR를 실행하고 입력을 대기하는 테스트 케이스.
	 * 사실 이 코드 작성한게 오래되서 while문이 왜 있는지 모르겠음.
	 * process 인스턴스로 뭔가 할 수 있을것 같기도 하고?
	 * 
	 * @throws IOException
	 * @author fixalot
	 */
//	@Test
	public void test() throws IOException {
		
		// TODO 미완성
		
		Runtime runtime = Runtime.getRuntime();
		@SuppressWarnings("unused")
		Process proc = runtime.exec("java -jar start.jar", null, new File("C:\\dev\\jetty-distribution-9.3.6.v20151106\\"));

		while (true) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			String input = bufferedReader.readLine();
			if ("shutdown".equals(input)) {
				logger.debug("### system shut down");
				System.exit(1);
			} else {
				logger.debug(input);
			}
		}

	}
}
