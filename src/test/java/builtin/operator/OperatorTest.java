package builtin.operator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 연산자 테스트 슈트
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class OperatorTest {

    @Test
    void preventingLostPrecision() {
        int a = 50000;
        int b = 100000;

        float result = a * b;
        assertThat(result)
                .isNotEqualTo(5000000000L) // result는 5000000000이 되어야 하지만 그렇지 않음
                .isEqualTo(7.050327E8f) // float 타입의 정밀도 한계로 인해 오차가 발생하기 때문
                .isEqualTo(705032704f); // 실제 계산된 값은 705032704
        log.debug("result: {}", result); // 7.050327E8

        float result2 = ((float) a) * b;
        assertThat(result2)
                .isEqualTo(5000000000L) // 미리 a나 b를 float로 바꿔주면 정밀도 손실을 발생할 수 있음
                .isEqualTo(5.0E9f);
        log.debug("result2: {}", result2); // 5.0E9
    }

    /**
     * 산술 연산자(arithmetic operator) 테스트
     */
    @Test
    void arithmeticOperator() {
        int n = 2665 / 1333; // 연산할 때 타입 명시가 없으면 int 타입으로 처리됨
        long l = 2665 / 1333;
        double d = 2665.0D / 1333.0D;

        assertThat(n).isEqualTo(1);
        assertThat(l).isEqualTo(1);
        assertThat(d).isEqualTo(1.9992498124531132783195798949737);

        assertThat(6 / 2 * (1 + 2)).isEqualTo(9);
        assertThat(6 / (2 * (1 + 2))).isEqualTo(1);
    }

    /**
     * 음수 테스트
     */
    @Test
    void negativeNumberTest() {
        int p = 123;
        assertThat(-p).isEqualTo(-123);
        assertThat(-(p)).isEqualTo(-123);
        assertThat(-1 * p).isEqualTo(-123);
        assertThat(-(p) + -p).isEqualTo(-246);

        int n = -400;
        assertThat(-n).isEqualTo(400);
        assertThat(-(n)).isEqualTo(400);
        assertThat(-1 * n).isEqualTo(400);
        assertThat(100 - n).isEqualTo(500);
        assertThat(-100 + -(n)).isEqualTo(300);
        assertThat(100 + n).isEqualTo(-300);
        assertThat(-100 + n).isEqualTo(-500);

    }

    /**
     * 할당 연산자<sup>assignment operator</sup> 테스트
     */
    @Test
    void assignmentOperator() {
        // 할당
        int n = 1;
        assertThat(n).isEqualTo(1);

        // 산술 연산 후 할당
        n += 2;
        assertThat(n).isEqualTo(3);
        n -= 1;
        assertThat(n).isEqualTo(2);
        n *= 5;
        assertThat(n).isEqualTo(10);
        n /= 2;
        assertThat(n).isEqualTo(5);

        // 변수 하나 이상을 한 번에 초기화
        int num = n = 9 + 1;
        assertThat(num).isEqualTo(10);
        assertThat(String.valueOf(num = 20)).isEqualTo("20");

        int a, b, c, d, e;
        a = b = c = d = e = 100; // 다섯개를 한 번에 조로록
        assertThat(a == b && b == c && c == d && d == e && 100 == e).isTrue(); // 당연히 다섯 변수의 값은 같음
    }

    /**
     * 음수 부호 테스트
     */
    @Test
    void negativeSign() {
        assertThat(-3 < 0);
        final int three = 3;
        assertThat(-(three)).isEqualTo(-3);
        assertThat(-three).isEqualTo(-3);
        assertThat(-(-three)).isEqualTo(3);
        assertThat(-(-(-three))).isEqualTo(-3);

        // convert to negative value
        assertThat(45 * -1).isEqualTo(-45);
        assertThat(90 * -1).isEqualTo(-90);
        assertThat(312 * -1).isEqualTo(-312);
    }

    @Test
    void ternaryOperator() {
        String first = "1";
        String second = "2";
        String third = "3";
        String fourth = "";

        String result = (!fourth.isEmpty()) ? fourth : (!third.isEmpty()) ? third : (!second.isEmpty()) ? second : first;
        assertThat(result).isEqualTo("3");
    }

    @Test
    void unaryOperator() {
        int a = 0;
        ++a;
        assertThat(a).isEqualTo(1); // 변수 반환 이전에 연산. 따라서 +1의 결과인 1
        assertThat(a).isEqualTo(1);
        assertThat(a).isEqualTo(1); // 변수 반환 이후에 연산. 따라서 +1 하기 전의 결과인 1
        a++;
        assertThat(a).isEqualTo(2);

        // 반복문에서 증감연산자는 피연산자의 어느쪽에 있어도 상관 없다.
        for (int i = 0; 2 > i; ++i) {
            log.debug("{}", i);
        }
        for (int i = 0; 2 > i; i++) {
            log.debug("{}", i);
        }
    }

    @Test
    void binaryOperator() {
        assertThat(1 + 1).isEqualTo(2);
    }

    @Test
    void modulusOperator() {
        assertThat(0 % 3).isEqualTo(0);
        assertThat(1 % 3).isEqualTo(1);
        assertThat(2 % 3).isEqualTo(2);
        assertThat(0).isEqualTo(0);
        assertThat(4 % 3).isEqualTo(1);
        assertThat(5 % 3).isEqualTo(2);
        assertThat(6 % 3).isEqualTo(0);

        // 특정 값보다 큰 수를 제거할 때 사용할 수 있음
        assertThat(41799 % 100).isEqualTo(99);
    }

    @Test
    void leftShiftOperator() {
        int a = 9;
        assertThat(Integer.toBinaryString(a)).isEqualTo("1001");
        assertThat(a << 2).isEqualTo(36);
        assertThat(Integer.toBinaryString(a << 2)).isEqualTo("100100");
    }

    @Test
    void rightShiftOperator() {
        int a = 9;
        assertThat(Integer.toBinaryString(a)).isEqualTo("1001");
        assertThat(Integer.toBinaryString(a >> 2)).isEqualTo("10");
        assertThat(a >> 2).isEqualTo(2);

        int b = -9;
        assertThat(Integer.toBinaryString(b)).isEqualTo("11111111111111111111111111110111");
        assertThat(Integer.toBinaryString(b >> 2)).isEqualTo("11111111111111111111111111111101");
        assertThat(b >> 2).isEqualTo(-3);
    }

    @Test
    void unsignedRightShiftOperator() {
        int a = 9; // 1001
        assertThat(Integer.toBinaryString(a)).isEqualTo("1001");
        assertThat(Integer.toBinaryString(a >>> 2)).isEqualTo("10");
        assertThat(a >>> 2).isEqualTo(2);

        int b = -9; // 11111111111111111111111110011100
        assertThat(Integer.toBinaryString(b)).isEqualTo("11111111111111111111111111110111");
        assertThat(b >>> 2).isEqualTo(1073741821);
        assertThat(Integer.toBinaryString(b >>> 2)).isEqualTo("111111111111111111111111111101");
    }
}
