package builtin.date;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class SimpleDateFormatTest {

    @Test
    void test() throws ParseException {
        Calendar cal = Calendar.getInstance();

        // 2013-02-19일 설정
        cal.set(2013, 1, 19);

        // 100일 후
        cal.add(Calendar.DAY_OF_MONTH, 100);
        cal.add(Calendar.DATE, 100);
        String str = String.format("%tF", cal);
        System.out.println("백일 후 : " + str);

        String s = "2013-02-26";
        String e = "2013-10-20";

        // 텍스트를 날짜로 변환
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sf.parse(s);
        Date d2 = sf.parse(e); // Date 형변환

        // 두날짜의 간격
        long dif = d2.getTime() - d1.getTime(); // 틱값으로 계산
        long dd = dif / (24 * 60 * 60 * 1000);
        System.out.println("두 날짜간 간격 : " + dd);

        // 시스템의 현재 날짜
        Date now = new Date();
        Calendar now2 = Calendar.getInstance();

        // Date 타입을 형식화
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
        String dateWithFormat = sdf.format(now);
        log.debug(dateWithFormat); // 2015년 07월 07일 20시 02분 19초

        // Calendar 타입을 형식화
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String calendarWithFormat = sdf2.format(now2.getTime());
        log.debug(calendarWithFormat); // 2015-07-07 20:01:53
    }
}
