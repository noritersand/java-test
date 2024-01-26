package builtin.time;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

import static org.assertj.core.api.Assertions.*;

/**
 * <p>ChronoField 테스트 슈트</p>
 * <p>ChronoField는 날짜를 조작하는 데 사용되는 표준 필드 집합이다.</p>
 */
class ChronoFieldTest {

    /**
     * <p>ChronoField.ALIGNED_WEEK_OF_MONTH 테스트</p>
     * <p>ALIGNED_WEEK_OF_MONTH는 특정 날짜가 해당 월의 몇 번째 주(aligned week)인지를 의미한다.</p>
     * <p>단순히 </p>
     */
    @Test
    void testAlignedWeekOfMonth() {
        assertThat(LocalDate.of(2023, 5, 1).get(ChronoField.ALIGNED_WEEK_OF_MONTH)).isEqualTo(1);
        assertThat(LocalDate.of(2023, 5, 8).get(ChronoField.ALIGNED_WEEK_OF_MONTH)).isEqualTo(2);
        assertThat(LocalDate.of(2023, 5, 11).get(ChronoField.ALIGNED_WEEK_OF_MONTH)).isEqualTo(2);
        assertThat(LocalDate.of(2023, 5, 29).get(ChronoField.ALIGNED_WEEK_OF_MONTH)).isEqualTo(5);
        assertThat(LocalDate.of(2025, 2, 1).get(ChronoField.ALIGNED_WEEK_OF_MONTH)).isEqualTo(1);
        assertThat(LocalDate.of(2025, 2, 2).get(ChronoField.ALIGNED_WEEK_OF_MONTH)).isEqualTo(1);
        assertThat(LocalDate.of(2025, 2, 3).get(ChronoField.ALIGNED_WEEK_OF_MONTH)).isEqualTo(1);
        assertThat(LocalDate.of(2025, 2, 4).get(ChronoField.ALIGNED_WEEK_OF_MONTH)).isEqualTo(1);
        assertThat(LocalDate.of(2025, 2, 5).get(ChronoField.ALIGNED_WEEK_OF_MONTH)).isEqualTo(1);
        assertThat(LocalDate.of(2025, 2, 6).get(ChronoField.ALIGNED_WEEK_OF_MONTH)).isEqualTo(1);
        assertThat(LocalDate.of(2025, 2, 7).get(ChronoField.ALIGNED_WEEK_OF_MONTH)).isEqualTo(1);
        assertThat(LocalDate.of(2025, 2, 8).get(ChronoField.ALIGNED_WEEK_OF_MONTH)).isEqualTo(2);
        assertThat(LocalDate.of(2025, 2, 9).get(ChronoField.ALIGNED_WEEK_OF_MONTH)).isEqualTo(2);
    }
}
