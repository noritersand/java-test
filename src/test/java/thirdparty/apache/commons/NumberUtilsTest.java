package thirdparty.apache.commons;

import org.apache.commons.lang3.math.NumberUtils;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
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
		assertTrue(NumberUtils.isDigits("0"));
		assertTrue(NumberUtils.isDigits("01"));
		assertTrue(NumberUtils.isDigits("58789"));
		
		assertFalse(NumberUtils.isDigits(""));
		assertFalse(NumberUtils.isDigits(" "));
		assertFalse(NumberUtils.isDigits("ONE"));
		
		assertFalse(NumberUtils.isDigits("3.14"));
		assertFalse(NumberUtils.isDigits("0x10"));
		assertFalse(NumberUtils.isDigits("58789123124L"));
		assertFalse(NumberUtils.isDigits("58789123124.924123D"));
		assertFalse(NumberUtils.isDigits("0124.11F"));
	}

	@Test
	public void testIsCreatable() {
		assertTrue(NumberUtils.isCreatable("0"));
		assertTrue(NumberUtils.isCreatable("01"));
		assertTrue(NumberUtils.isCreatable("58789"));
		
		assertFalse(NumberUtils.isCreatable(""));
		assertFalse(NumberUtils.isCreatable(" "));
		assertFalse(NumberUtils.isCreatable("ONE"));
		
		assertTrue(NumberUtils.isCreatable("3.14"));
		assertTrue(NumberUtils.isCreatable("0x10"));
		assertTrue(NumberUtils.isCreatable("58789123124L"));
		assertTrue(NumberUtils.isCreatable("58789123124.924123D"));
		assertTrue(NumberUtils.isCreatable("0124.11F"));
	}
}
