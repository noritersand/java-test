package builtin.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DayOfWeek 클래스 테스트
 */
@Slf4j
public class DayOfWeekTest {

    @Test
    void basicUsages() {
        LocalDate instance = LocalDate.parse("2022-12-31");
        DayOfWeek dayOfWeek = instance.getDayOfWeek();
        assertThat(dayOfWeek.ordinal()).isEqualTo(5);
        assertThat(dayOfWeek.getValue()).isEqualTo(6);
        assertThat(dayOfWeek).isEqualTo(DayOfWeek.SATURDAY);
        assertThat(DayOfWeek.MONDAY.getValue()).isEqualTo(1);
        assertThat(DayOfWeek.MONDAY.ordinal()).isEqualTo(0);
    }
}
