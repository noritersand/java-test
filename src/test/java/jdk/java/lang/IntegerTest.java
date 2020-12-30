package jdk.java.lang;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * java.lang.Integer 테스트
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class IntegerTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(IntegerTest.class);

	/**
	 * 10진수를 2진수로 변환
	 * 
	 * @author fixalot
	 */
	@Test
	public void convertToBinary() {
		Assert.assertEquals("0", Integer.toBinaryString(0));
		Assert.assertEquals("1010", Integer.toBinaryString(10));
		Assert.assertEquals("11111111", Integer.toBinaryString(255));
		Assert.assertEquals("10000000000", Integer.toBinaryString(1024));
		Assert.assertEquals("10000000000000000", Integer.toBinaryString(65536));
	}
	
	/**
	 * 10진수를 8진수로 변환
	 * 
	 * @author fixalot
	 */
	@Test
	public void convertToOctonary() {
		Assert.assertEquals("0", Integer.toOctalString(0));
		Assert.assertEquals("12", Integer.toOctalString(10));
		Assert.assertEquals("377", Integer.toOctalString(255));
		Assert.assertEquals("2000", Integer.toOctalString(1024));
		Assert.assertEquals("200000", Integer.toOctalString(65536));
	}
	
	/**
	 * 10진수를 16진수로 변환
	 * 
	 * @author fixalot
	 */
	@Test
	public void convertToHexadecimal() {
		Assert.assertEquals("0", Integer.toHexString(0));
		Assert.assertEquals("a", Integer.toHexString(10));
		Assert.assertEquals("ff", Integer.toHexString(255));
		Assert.assertEquals("400", Integer.toHexString(1024));
		Assert.assertEquals("10000", Integer.toHexString(65536));
	}
}