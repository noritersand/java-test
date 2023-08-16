package builtin.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.UnknownFormatConversionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * {@link Formatter} 테스트
 *
 * @author fixalot
 * @since 2018-10-31
 */
@Slf4j
public class FormatterTest {

    /**
     * %와 알파뱃의 조합으로
     */
    @Test
    void basicUsage () {
        assertThat(String.format("크기%,d, 파일%,d개, 폴더%,d\n", 1024, 3, 1))
                .isEqualTo("크기1,024, 파일3개, 폴더1\n");

        assertThat(String.format("시작%4s끝", "값"))
                .isEqualTo("시작   값끝");

        assertThat(String.format("%% = %s", "percent sign."))
                .isEqualTo("% = percent sign.");

        assertThat(String.format("%1$s is %1$s", "a", "b"))
                .isEqualTo("a is a");

        assertThat(String.format("%1$s is %1$s", "a", "b"))
                .isEqualTo("a is a");
    }

    /**
     * 변환 문자 목록
     */
    @Test
    void conversionCharacters() {
        // 불리언
        assertThat(String.format("%b, %b, %B, %B", null, 123, false, Boolean.TRUE))
                .isEqualTo("false, true, FALSE, TRUE");

        // Integer.toHexString()
        assertThat(String.format("%h, %h, %H, %H, %H", null, 10, 16, 17, 28))
                .isEqualTo("null, a, 10, 11, 1C");

        // 문자열
        assertThat(String.format("%s, %S, %s", null, "abcd", 1234))
                .isEqualTo("null, ABCD, 1234");

        // 유니코드
        assertThat(String.format("%c, %c, %C", 0x0067, 0x0047, 0x0069))
                .isEqualTo("g, G, I");

        // 10진수
        assertThat(String.format("%d", 2147483648L))
                .isEqualTo("2147483648");

        // 8진수
        assertThat(String.format("%o, %o, %o", 7, 8, 14))
                .isEqualTo("7, 10, 16");

        // 16진수
        assertThat(String.format("%x, %x, %X", 16, 15, 15))
                .isEqualTo("10, f, F");

        // 부동 소수점 숫자를 지수 표기법으로
        assertThat(String.format("%e, %E", 20000.0f, 65536.888f))
                .isEqualTo("2.000000e+04, 6.553689E+04");

        // 부동 소수점 숫자를 소수점 표기법으로
        assertThat(String.format("%f, %f", 20000.0f, 65536.256f))
                .isEqualTo("20000.000000, 65536.257813");

        // 부동 소수점의 일반 표현
        assertThat(String.format("%g, %g, %g, %g, %G", 20.232f, 123.456f, 999999.0f, 1000000.0f, 1000020.0f))
                .isEqualTo("20.2320, 123.456, 999999, 1.00000e+06, 1.00002E+06");

        // 16진수 부동 소수점 표기법
        assertThat(String.format("%a, %a, %a, %a, %A", 20.232f, 123.456f, 999999.0f, 1000000.0f, 1000020.0f))
                .isEqualTo("0x1.43b646p4, 0x1.edd2f2p6, 0x1.e847ep19, 0x1.e848p19, 0X1.E84A8P19");

        // 퍼센트
        assertThat(String.format("%%", 0))
                .isEqualTo("%");

        LocalDateTime now = LocalDateTime.of(2023, 12, 17, 14, 53, 46, 123);
        Date now2 = Date.from(now.toInstant(ZoneOffset.ofHours(9)));

        // 시간
        assertThat(String.format("%1$tp %1$tI시 %1$tM분 %1$tS초", now))
                .isEqualTo("오후 02시 53분 46초");

        assertThat(String.format("%1$tz, %1$tZ, %tQ", now2))
                .isEqualTo("+0900, KST, 1702792426000");

        // 날짜
        assertThat(String.format("%1$tB, %1$tb, %1$th", now))
                .isEqualTo("12월, 12월, 12월");

        assertThat(String.format("%1$tA, %1$ta", now))
                .isEqualTo("일요일, 일");

        assertThat(String.format("%1$tC, %1$tY, %1$ty", now))
                .isEqualTo("20, 2023, 23");

        assertThat(String.format("%1$tm월 %1$td일 (%1$te일, %1$tj일)", now))
                .isEqualTo("12월 17일 (17일, 351일)");

        // 날짜와 시간
        assertThat(String.format("%1$tR", now).toString())
                .isEqualTo("14:53");

        assertThat(String.format("%1$tT", now))
                .isEqualTo("14:53:46");

        assertThat(String.format("%1$tr", now))
                .isEqualTo("02:53:46 오후");

        assertThat(String.format("%1$tD", now))
                .isEqualTo("12/17/23");

        assertThat(String.format("%1$tF", now))
                .isEqualTo("2023-12-17");

        assertThat(String.format("%1$tc", now2))
                .isEqualTo("일 12월 17 14:53:46 KST 2023");

        // ## 날짜: %t에 어떤 포맷 혹은 어느 자리값을 출력하는지를 명시하는 방식

        // ### %tF: 년월일의 ISO 포맷
        assertThat(String.format("%tF", LocalDateTime.of(2022, Month.DECEMBER, 2, 10, 12))).isEqualTo("2022-12-02");

        // ### %tf는 없음
        assertThatThrownBy(() -> {
            String.format("%tf", LocalDateTime.now());
        }).isInstanceOf(UnknownFormatConversionException.class).hasMessage("Conversion = 'tf'");
        
    }

    /**
     * flag
     */
    @Test
    void testFlags() {
        assertThat(String.format("%-10s", "helloooo"))
                .isEqualTo("helloooo  ");

        assertThat(String.format("%x, %#x", 123, 123))
                .isEqualTo("7b, 0x7b");

        assertThat(String.format("%d, %+d, [% d], [% d]", -99, 123, 456, -456))
                .isEqualTo("-99, +123, [ 456], [-456]");

        assertThat(String.format("%x, %#x", 123, 123))
                .isEqualTo("7b, 0x7b");

        assertThat(String.format("%05d", 123))
                .isEqualTo("00123");

        assertThat(String.format("%,d", 7654321))
                .isEqualTo("7,654,321");

        assertThat(String.format("%d, %(d, %(d", -123, 123, -123))
                .isEqualTo("-123, 123, (123)");
    }

    @Test
    void testWidth() {
        assertThat(String.format("%10s", "hi"))
                .isEqualTo("        hi");
    }

    @Test
    void testPrecision() {
        float value = 6.5536f;
        assertThat(String.format("%f", value)).isEqualTo("6.553600");
        assertThat(String.format("%.2f", value)).isEqualTo("6.55");
        assertThat(String.format("%.10f", value)).isEqualTo("6.5535998344");
    }

}
