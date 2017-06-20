package laboratory.jdk.java.lang;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharacterTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CharacterTest.class);
	
	@Test
	public void compare() {
		char a = 'a';
		char b = 'b';
		Character aa = 'a';
		Character bb = 'b';

		// 동등 비교
		Assert.assertTrue(a == 'a');
		Assert.assertFalse(a == b);
		Assert.assertTrue(a == aa);
		Assert.assertTrue(bb.equals(b));
		
		// String과 비교
		Assert.assertFalse("a".equals('a')); // equals()로는 다른 타입끼리 비교 불가
		Assert.assertTrue(a == "a".charAt(0));
		
		// 새로 만든 인스턴스와 비교
		Character inst = new Character('a');
		Assert.assertTrue(a == inst); // 원시-객체는 객체가 원시타입으로 변환되므로 동등비교 가능.
		Assert.assertTrue(aa != inst); // 객체끼리는 값이 같아도 동등비교 불가.
		Assert.assertFalse(inst.equals("a")); // equals()로는 다른 타입끼리 비교 불가
		Assert.assertTrue(inst.equals("a".charAt(0)));
		Assert.assertTrue(inst == "a".charAt(0));
	}
	
	@Test
	@SuppressWarnings({ "null", "unused" })
	public void compareWithNull() {
		try {
			Character e = null;
			boolean equal = e == 'b'; // NullPointerException
			
			Integer i = null;
			equal = i == 1; // NullPointerException
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void getASCIICode() {
		final String binary = "5";
		final String alphabet = "a";
		final String korean = "한";
		logger.debug(String.valueOf((int) binary.charAt(0)));
		logger.debug(String.valueOf((int) alphabet.charAt(0)));
		logger.debug(String.valueOf((int) korean.charAt(0)));
	}
	
	@Test
	public void fromASCIICode() {
		char a = 65;
		char b = 98;
		Assert.assertEquals('A', a);
		Assert.assertEquals('b', b);
	}
}
