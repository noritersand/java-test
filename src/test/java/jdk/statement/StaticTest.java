package jdk.statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * static 키워드 테스트
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class StaticTest {
	private static final Logger logger = LoggerFactory.getLogger(StaticTest.class);

	/**
	 * 스태틱 블록은 언제 실행되나.
	 */
	static {
		@SuppressWarnings("unused")
		int a = 1; // break here
	}
	
	/**
	 * 스태틱 멤버는 언제 초기화되나
	 */
	public static String textA = "욜루";
	
	/**
	 * 스태틱 블록이나 스태틱 멤버가 초기화&실행되는 시점은, 정확하진 않지만 브레이크 포인트로 테스트해보면 JVM이 올라갈 때인걸로 추정된다. 
	 */
	@SuppressWarnings("unused")
	private static String textB = "욜룰루";
	
	public static void main(String[] args) {
		logger.debug("안녕 세상아!");
	}
}
