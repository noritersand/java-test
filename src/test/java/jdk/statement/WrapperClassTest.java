package jdk.statement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class WrapperClassTest {
	private static final Logger logger = LoggerFactory.getLogger(WrapperClassTest.class);

	@Test
	public void copy() {
		final Integer a = Integer.valueOf(123);
		Integer b = a;
		assertEquals(123, b.intValue());
		b = 456; // b = new Integer(456); String과 마찬가지로 새 객체가 할당된다.
		assertEquals(123, a.intValue());
		assertEquals(456, b.intValue());
	}
	
	@Test
	public void testEquals() {
		Long a = Long.valueOf(12345);
		Long b = Long.valueOf(12345);
		assertFalse(a == b); // wrapper 타입은 동등 연산자에서 객체 아이디를 비교하지 값을 비교하지 않음.
		assertTrue(a.equals(b)); // correct
	}
	
	@Test
	public void testPassedByValue() {
		Integer num = 456;
		changeValue(num); // 내부에서 123으로 변경
		// 그래도 num은 456으로 유지된다. wrapper 타입이 일반적인 reference 타입과 다른 특징임.
		assertEquals(Integer.valueOf(456), num);
	}
	
	private void changeValue(Integer num) {
		num = 123; // 이 코드는 num = new Integer(123) 과 같다.
	}
	
	/**
	 * null을 명시적으로 형변환하는 것은 null-safe함
	 * 
	 * @author fixalot
	 */
	@Test
	public void testNullSafe() {
		Object a = null;
		String txt = (String) a;
		assertNull(txt);
	}
	
	/**
	 * null을 (오토언박싱이든 아니든) 원시타입으로 형 변환 하려고 하면 null-safe하지 않음.
	 * 
	 * @author fixalot
	 */
	@Test
	public void testNullSafe2() {
		try {
			Object chuckNorris = null;
			@SuppressWarnings({ "unused", "null" })
			int imgonnabenull = (Integer) chuckNorris;
		} catch (Exception e) {
			logger.debug("예외 발생#1: {}", e.getLocalizedMessage());
		}
		
		try {
			Integer chucknorris = null;
			@SuppressWarnings({ "unused", "null" })
			int imgonnabenull = chucknorris;
		} catch (Exception e) {
			logger.debug("예외 발생#2: {}", e.getLocalizedMessage());
		}
	}
}
