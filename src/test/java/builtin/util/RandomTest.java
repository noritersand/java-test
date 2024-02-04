package builtin.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

/**
 * 랜덤 관련 테스트 케이스들. {@link testbed.util.RandomGenerator}도 살펴볼 것
 * 
 * @author fixalot
 * @since 2018-01-23
 */
@Slf4j
class RandomTest {

    @Test
    void testNextInt() {
        for (int i = 0; 1000 > i; ++i) {
            Random random = new Random();

            int num = random.nextInt(); // int의 최솟값 이상 최댓값 미만 사이의 랜덤값 추출
            assertThat(Integer.MIN_VALUE <= num && Integer.MAX_VALUE > num).isTrue();

            num = random.nextInt(5); // 0 이상 5 미만 사이의 랜덤값 추출
            assertThat(0 <= num && 5 > num).isTrue();
        }
    }

    /**
     * 얘는 뭥미?
     *
     * @author noritersand
     */
    @Test
    void testInts() {
        Random random = new Random();
        IntStream a = random.ints();
        assertEquals(9223372036854775807L, a.count());
        a = random.ints(100);
        assertEquals(100L, a.count());
    }

    /**
     * List의 요소를 난수로 꺼내기
     *
     * @author fixalot
     */
    @Test
    void getRandomElement() {
        List<String> list = new LinkedList<>();
        list.add("하나");
        list.add("둘");
        list.add("셋");
        list.add("넷");
        list.add("다섯");
        list.add("여섯");
        list.add("일곱");
        list.add("여덟");
        list.add("아홉");
        list.add("열");
        for (int i = 0; 100 > i; ++i) {
            String element = list.get(new Random().nextInt(list.size()));
            assertThat(element).isNotNull();
            assertThat(element.isEmpty()).isFalse();
            assertThat(element instanceof String).isTrue();
            if (99 == i) {
                log.debug(element);
            }
        }
    }

    @Test
    void compareTypes() {
        for (int i = 0; i < 10; ++i) {
            Integer intValue = (int) (Math.random() * 1000000);
            log.debug("intValue: {}", intValue);
        }

        for (int i = 0; i < 10; ++i) {
            Random random = new Random();
            log.debug("random.nextInt(): {}", random.nextInt());
        }

        for (int i = 0; i < 10; ++i) {
            Random random = new Random();
            log.debug("random.nextInt(10): {}", random.nextInt(999999));
        }

        for (int i = 0; i < 10; ++i) {
            SecureRandom secureRandom = new SecureRandom();
            log.debug("secureRandom.nextInt(10): {}", secureRandom.nextInt(999999));
        }

        for (int i = 0; i < 10; ++i) {
            int length = 3; // 원하는 난수의 길이 (바이트 단위)

            SecureRandom random = new SecureRandom();
            byte[] values = new byte[length];
            random.nextBytes(values); // 난수 생성

            log.debug("random.nextBytes: {}", values);

            // 바이트 배열을 헥사 문자열로 변환 (옵션)
            StringBuilder sb = new StringBuilder();
            for (byte b : values) {
                sb.append(String.format("%02x", b));
            }

            log.debug("sb.toString(): {}", sb);
        }

    }

}
