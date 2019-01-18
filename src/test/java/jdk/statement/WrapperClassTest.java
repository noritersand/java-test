package jdk.statement;

import org.junit.Assert;
import org.junit.Test;
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
		Assert.assertEquals(123, b.intValue());
		b = 456; // b = new Integer(456); String과 마찬가지로 새 객체가 할당된다.
		Assert.assertEquals(123, a.intValue());
		Assert.assertEquals(456, b.intValue());
	}
	
	@Test
	public void testEquals() {
		Long a = Long.valueOf(12345);
		Long b = Long.valueOf(12345);
		Assert.assertFalse(a == b); // wrapper 타입은 동등 연산자에서 객체 아이디를 비교하지 값을 비교하지 않음.
		Assert.assertTrue(a.equals(b)); // correct
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
		Assert.assertNull(txt);
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
