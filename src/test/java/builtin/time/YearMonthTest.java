package builtin.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * YearMonth 클래스 테스트
 */
@Slf4j
public class YearMonthTest {

    @Test
    void basicUsages() {
        YearMonth ins = YearMonth.parse("2022-09");
        assertThat(ins.toString()).isEqualTo("2022-09");

        String yyyyMM = ins.format(DateTimeFormatter.ofPattern("yyyyMM"));
        assertThat(yyyyMM).isEqualTo("202209");

        assertThat(ins.getYear()).isEqualTo(2022);
        assertThat(ins.getMonth()).isEqualTo(Month.SEPTEMBER);
        assertThat(ins.getMonthValue()).isEqualTo(9);
    }

}
