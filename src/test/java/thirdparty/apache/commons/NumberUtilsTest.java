package thirdparty.apache.commons;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * apache commons NUmberUtils 테스트
 *
 * @author fixalot
 * @since 2019-03-21
 */
@Slf4j
public class NumberUtilsTest {

    @Test
    void testIsNumber() {
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
    void testIsCreatable() {
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

    @Test
    void testNullSafe() {
        assertEquals(0, NumberUtils.toLong(null));
        assertEquals(0, NumberUtils.toLong(""));
    }
}
