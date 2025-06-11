package builtin.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * LocalDate 클래스 테스트
 */
@Slf4j
public class LocalDateTest {

    @Test
    void basicUsages() {
        LocalDate today = LocalDate.now();
        LocalDate ins = LocalDate.parse("2021-01-01", DateTimeFormatter.ISO_LOCAL_DATE);// DateTimeFormatter.ofPattern("yyyy-MM-dd")

        assertThat(ins.getYear()).isEqualTo(2021);
        assertThat(ins.getMonthValue()).isEqualTo(1);
        assertThat(ins.toString()).isEqualTo("2021-01-01");
        assertThat(LocalDate.of(2017, Month.DECEMBER, 31).toString()).isEqualTo("2017-12-31");
        assertThat(LocalDateTime.of(2017, Month.APRIL, 10, 23, 49).toString()).isEqualTo("2017-04-10T23:49");

        LocalDate ld = LocalDate.ofInstant(Instant.ofEpochMilli(1724057691992L), ZoneId.of("Asia/Seoul"));
        assertThat(ld.toString()).isEqualTo("2024-08-19");
    }

    @Test
    void withFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate someDay = LocalDate.parse("20210101", formatter);
        assertThat(someDay.getYear()).isEqualTo(2021);
        assertThat(someDay.getMonthValue()).isEqualTo(1);
        assertThat(someDay.getDayOfMonth()).isEqualTo(1);

        assertThatThrownBy(() -> {
            LocalDate someDay2 = LocalDate.parse("20220202");
        }).isInstanceOf(DateTimeParseException.class).hasMessage("Text '20220202' could not be parsed at index 0");
    }
}
