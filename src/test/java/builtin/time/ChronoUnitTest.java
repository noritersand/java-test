package builtin.time;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * java.time.temporal.ChronoUnit 테스트
 */
class ChronoUnitTest {

    @Test
    void splitDate() {
        LocalDate start = new GregorianCalendar(2016, 2, 5).getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = new GregorianCalendar(2016, 2, 11).getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long periodDays = ChronoUnit.DAYS.between(start, end);
        List<LocalDate> list = new LinkedList<>();
        for (int i = 0; i < periodDays; i++) {
            list.add(start.plusDays(i));
        }
        assertEquals("[2016-03-05, 2016-03-06, 2016-03-07, 2016-03-08, 2016-03-09, 2016-03-10]", list.toString());
    }

    /**
     * 날짜간 차이를 전체 일 수로 계산
     */
    @Test
    void calculateDays() {
        LocalDate targetDay = LocalDate.of(2017, Month.DECEMBER, 31);
        LocalDate today = LocalDate.of(2017, Month.JANUARY, 24);
        long period2 = ChronoUnit.DAYS.between(today, targetDay);
        assertThat(period2).isEqualTo(341);
    }
}
