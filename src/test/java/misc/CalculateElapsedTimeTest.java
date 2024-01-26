package misc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class CalculateElapsedTimeTest {

    @Test
    void calculate() {
        long start = new GregorianCalendar(2016, 2, 5).getTimeInMillis();
        long end = new GregorianCalendar(2017, 2, 5).getTimeInMillis();
        long elapsedDays = (end - start) / 1000 / 60 / 60 / 24;
        assertEquals(365, elapsedDays);

        start = new GregorianCalendar(2016, 2, 5, 21, 59, 59).getTimeInMillis();
        end = new GregorianCalendar(2016, 2, 5, 23, 59, 59).getTimeInMillis();
        elapsedDays = (end - start) / 1000;
        assertTrue(86400 > elapsedDays); // 2시간 전이라 86400초(하루) 보다 작다.

        start = new GregorianCalendar(2016, 2, 5).getTimeInMillis();
        end = new GregorianCalendar(2016, 2, 5, 23, 59, 59).getTimeInMillis();
        elapsedDays = (end - start) / 1000;
        assertTrue(86400 > elapsedDays); // 23시간 59분 59초 전

        start = new GregorianCalendar(2016, 2, 4).getTimeInMillis();
        end = new GregorianCalendar(2016, 2, 6).getTimeInMillis();
        elapsedDays = (end - start) / 1000;
        assertTrue(86400 < elapsedDays); // 이틀 전이라 86400초(하루) 보다 크다.
    }
}
