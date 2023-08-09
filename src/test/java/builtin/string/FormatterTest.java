package builtin.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
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
    private final StringBuffer sb = new StringBuffer();
//    private final Formatter formatter = new Formatter(sb);

    private final StringFormatter formatter = new StringFormatter();

    private static final class StringFormatter {
        private final StringBuffer sb = new StringBuffer();
        private final Formatter formatter = new Formatter(sb);

        public StringFormatter format(String format, Object... args) {
            this.formatter.format(format, args);
            return this;
        }

        public String toString() {
            return sb.toString();
        }

        public void clear() {
            sb.setLength(0);
        }
    }

    private final Object customFormatter = new Object() {

    };

//    private String getFormatterOutput(String format, Object ... args) {
//        this.formatter.format(format, args);
//        String meIzDaResult = sb.toString();
//        sb.setLength(0);
//        return meIzDaResult;
//    }

    @Test
    void test() {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
//        formatter.format(Locale.getDefault(Locale.Category.FORMAT), "%d", 1234);

        formatter.format("크기%,d, 파일%,d개, 폴더%,d\n", 1024, 3, 1);
        assertThat(sb.toString()).isEqualTo("크기1,024, 파일3개, 폴더1\n");
        sb.setLength(0);
    }

    /**
     * 변환 문자 목록
     */
    @Test
    void conversionCharacters() {
//        StringBuilder sb = new StringBuilder();
//        Formatter formatter = new Formatter(sb);
        // 복사용
//        assertThat(formatter.format("", 0)).isEqualTo("");


        Calendar cal = Calendar.getInstance();
        System.out.println("오늘 : " + String.format("%tF", cal));

        cal.add(Calendar.MONTH, -3);
        System.out.println("세달전 : " + String.format("%tF", cal));

        String s = String.format("%, d", 1000000000);
        System.out.println(s);   // I have 44444.44 bugs to fix

        String fo = String.format("I have %.2f bugs to fix.", 44444.444);
        System.out.println(fo);   // I have 2,231,323.23 bugs to fix.

        String fo1 = String.format("I have %,.2f bugs to fix ", 2231323.23132);
        System.out.println(fo1);   // 1,323,131,123.1

        String fo2 = String.format("%,6.1f", 1323131123.133123213);
        System.out.println(fo2);   // 금 9월 05 10:55:14 KST 2008

        String fo3 = String.format("%tc", new Date());
        System.out.println(fo3);   // 10:55:14 오전

        String fo4 = String.format("%tr", new Date());
        System.out.println(fo4);   // 금요일 9월 20

        String fo5 = String.format("%tA %tB %tC", new Date(), new Date(), new Date());
        System.out.println(fo5);   // 금요일 9월 20

        // 날짜: %t에 어떤 포맷 혹은 어느 자리값을 출력하는지를 명시하는 방식
        assertThat(formatter.format("%tF", LocalDateTime.of(2022, Month.DECEMBER, 2, 10, 12)).toString()).isEqualTo("2022-12-02");
        formatter.clear();

        // tf는 없음
        assertThatThrownBy(() -> {
            formatter.format("%tf", LocalDateTime.now());
        }).isInstanceOf(UnknownFormatConversionException.class).hasMessage("Conversion = 'tf'");


        // 문자열: %s
        assertThat(formatter.format("아무고토 %s", "모태쬬").toString()).isEqualTo("아무고토 모태쬬");
        formatter.clear();
    }


}
