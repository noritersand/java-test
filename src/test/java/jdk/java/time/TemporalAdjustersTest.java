package jdk.java.time;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link java.time.temporal.TemporalAdjusters} 테스트 슈트
 *
 * @author fixalot
 * @since 2022-12-13
 */
public class TemporalAdjustersTest {

    /**
     * <p>다음 주 월요일 구하기</p>
     */
    @Test
    public void getNextMonday() {
        LocalDate today = LocalDate.parse("2022-10-22");
        LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        assertEquals(LocalDate.parse("2022-10-24"), nextMonday);
    }

    /**
     * <p>다음 달의 첫 날 구하기</p>
     */
    @Test
    public void getFirstDayOfMonth() {
        LocalDate today = LocalDate.parse("2022-10-22");
        LocalDate nextMonday2 = today.with(TemporalAdjusters.firstDayOfNextMonth());
        // 아래처럼 한 달 더하고 첫 날 구하는 것과 같음
//		LocalDate nextMonday = today.plusMonths(1)
//				.with(TemporalAdjusters.firstDayOfMonth());
        assertEquals(LocalDate.parse("2022-11-01"), nextMonday2);
    }

    /**
     * <p>다음 년도의 같은 월일 구하기</p>
     */
    @Test
    public void getNextYearsSameDay() {
        LocalDate today = LocalDate.parse("2022-10-22");
        LocalDate nextYearsDay = today.plusYears(1);
        assertEquals(LocalDate.parse("2023-10-22"), nextYearsDay);
        // 아래처럼 한 달 더하고 첫 날 구하는 것과 같음
    }
}
