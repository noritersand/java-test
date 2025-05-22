package builtin.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * java.time.Period 테스트
 */
@Slf4j
public class PeriodTest {

    /**
     * 날짜간 차이를 년, 월, 일로 나눠 계산
     */
    @Test
    void calculateDays() {
        LocalDate targetDay = LocalDate.parse("20171229", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate today = LocalDate.parse("20170228", DateTimeFormatter.BASIC_ISO_DATE);
        Period period = Period.between(today, targetDay);
        assertThat(period.getYears()).isEqualTo(0);
        assertThat(period.getMonths()).isEqualTo(10);
        assertThat(period.getDays()).isEqualTo(1);
    }

}
