package jdk.java.lang;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoubleTest {

    @Test
    public void handleDecimalPlaces() {
        int portion = 18;
        int total = 26;
        double result = ((double) portion / total) * 100;
        assertEquals(69.23076923076923, result);
        assertEquals(69, Math.floor(result));

        BigDecimal bd = new BigDecimal(result);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        double roundedValue = bd.doubleValue();
        assertEquals(69.23, roundedValue);

    }
}
