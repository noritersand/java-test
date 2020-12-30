package thirdparty.apache.commons;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * apache commons NUmberUtils 테스트
 * 
 * @since 2019-03-21
 * @author fixalot
 */
public class NumberUtilsTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(NumberUtilsTest.class);

	@Test
	public void testIsNumber() {
		Assert.assertTrue(NumberUtils.isDigits("0"));
		Assert.assertTrue(NumberUtils.isDigits("01"));
		Assert.assertTrue(NumberUtils.isDigits("58789"));
		
		Assert.assertFalse(NumberUtils.isDigits(""));
		Assert.assertFalse(NumberUtils.isDigits(" "));
		Assert.assertFalse(NumberUtils.isDigits("ONE"));
		
		Assert.assertFalse(NumberUtils.isDigits("3.14"));
		Assert.assertFalse(NumberUtils.isDigits("0x10"));
		Assert.assertFalse(NumberUtils.isDigits("58789123124L"));
		Assert.assertFalse(NumberUtils.isDigits("58789123124.924123D"));
		Assert.assertFalse(NumberUtils.isDigits("0124.11F"));
	}

	@Test
	public void testIsCreatable() {
		Assert.assertTrue(NumberUtils.isCreatable("0"));
		Assert.assertTrue(NumberUtils.isCreatable("01"));
		Assert.assertTrue(NumberUtils.isCreatable("58789"));
		
		Assert.assertFalse(NumberUtils.isCreatable(""));
		Assert.assertFalse(NumberUtils.isCreatable(" "));
		Assert.assertFalse(NumberUtils.isCreatable("ONE"));
		
		Assert.assertTrue(NumberUtils.isCreatable("3.14"));
		Assert.assertTrue(NumberUtils.isCreatable("0x10"));
		Assert.assertTrue(NumberUtils.isCreatable("58789123124L"));
		Assert.assertTrue(NumberUtils.isCreatable("58789123124.924123D"));
		Assert.assertTrue(NumberUtils.isCreatable("0124.11F"));
	}
}
