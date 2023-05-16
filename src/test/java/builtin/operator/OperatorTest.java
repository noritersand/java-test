package builtin.operator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 연산자 테스트 슈트
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class OperatorTest {

    /**
     * 산술 연산자(arithmetic operator) 테스트
     */
    @Test
    void arithmeticOperator() {
        int n = 2665 / 1333; // 연산할 때 타입 명시가 없으면 int 타입으로 처리됨
        long l = 2665 / 1333;
        double d = 2665.0D / 1333.0D;

        assertEquals(1, n);
        assertEquals(1, l);
        assertEquals(1.9992498124531132783195798949737, d);

        assertEquals(9, 6 / 2 * (1 + 2));
        assertEquals(1, 6 / (2 * (1 + 2)));
    }

    /**
     * 음수 테스트
     */
    @Test
    void negativeNumberTest() {
        int p = 123;
        assertEquals(-123, -p);
        assertEquals(-123, -(p));
        assertEquals(-123, -1 * p);
        assertEquals(-246, -(p) + -p);

        int n = -400;
        assertEquals(400, -n);
        assertEquals(400, -(n));
        assertEquals(400, -1 * n);
        assertEquals(500, 100 - n);
        assertEquals(300, -100 + -(n));
        assertEquals(-300, 100 + n);
        assertEquals(-500, -100 + n);

    }

    /**
     * 할당 연산자<sup>assignment operator</sup> 테스트
     */
    @Test
    void assignmentOperator() {
        // 할당
        int n = 1;
        assertEquals(1, n);

        // 산술 연산 후 할당
        n += 2;
        assertEquals(3, n);
        n -= 1;
        assertEquals(2, n);
        n *= 5;
        assertEquals(10, n);
        n /= 2;
        assertEquals(5, n);

        // 변수 하나 이상을 한 번에 초기화
        int num = n = 9 + 1;
        assertEquals(10, num);
        assertEquals("20", String.valueOf(num = 20));

        // 좀 더 많이 해볼까?
        int a, b, c, d, e;
        a = b = c = d = e = 100; // 다섯개를 한 번에 조로록
        assertTrue(a == b && b == c && c == d && d == e && 100 == e); // 당연히 다섯 변수의 값은 같음
    }

    /**
     * 음수 부호 테스트
     */
    @Test
    void negativeSign() {
        assertTrue(-3 < 0);
        final int three = 3;
        assertEquals(-3, -(three));
        assertEquals(-3, -three);
        assertEquals(3, -(-three));
        assertEquals(-3, -(-(-three)));

        // convert to negative value
        assertEquals(-45, 45 * -1);
        assertEquals(-90, 90 * -1);
        assertEquals(-312, 312 * -1);
    }

    @Test
    void ternaryOperator() {
        String first = "1";
        String second = "2";
        String third = "3";
        String fourth = "";

        String result = (!fourth.isEmpty()) ? fourth : (!third.isEmpty()) ? third : (!second.isEmpty()) ? second : first;
        assertEquals("3", result);
    }

    @Test
    void unaryOperator() {
        int a = 0;
        ++a;
        assertEquals(1, a); // 변수 반환 이전에 연산. 따라서 +1의 결과인 1
        assertEquals(1, a);
        assertEquals(1, a); // 변수 반환 이후에 연산. 따라서 +1 하기 전의 결과인 1
        a++;
        assertEquals(2, a);

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
        assertEquals(2, 1 + 1);
    }

    @Test
    void modulusOperator() {
        assertEquals(0, 0 % 3);
        assertEquals(1, 1 % 3);
        assertEquals(2, 2 % 3);
        assertEquals(0, 0);
        assertEquals(1, 4 % 3);
        assertEquals(2, 5 % 3);
        assertEquals(0, 6 % 3);

        // 특정 값보다 큰 수를 제거할 때 사용할 수 있음
        assertEquals(99, 41799 % 100);
    }

    @Test
    void leftShiftOperator() {
        int a = 9;
        assertEquals("1001", Integer.toBinaryString(a));
        assertEquals(36, a << 2);
        assertEquals("100100", Integer.toBinaryString(a << 2));
    }

    @Test
    void rightShiftOperator() {
        int a = 9;
        assertEquals("1001", Integer.toBinaryString(a));
        assertEquals("10", Integer.toBinaryString(a >> 2));
        assertEquals(2, a >> 2);

        int b = -9;
        assertEquals("11111111111111111111111111110111", Integer.toBinaryString(b));
        assertEquals("11111111111111111111111111111101", Integer.toBinaryString(b >> 2));
        assertEquals(-3, b >> 2);
    }

    @Test
    void unsignedRightShiftOperator() {
        int a = 9; // 1001
        assertEquals("1001", Integer.toBinaryString(a));
        assertEquals("10", Integer.toBinaryString(a >>> 2));
        assertEquals(2, a >>> 2);

        int b = -9; // 11111111111111111111111110011100
        assertEquals("11111111111111111111111111110111", Integer.toBinaryString(b));
        assertEquals(1073741821, b >>> 2);
        assertEquals("111111111111111111111111111101", Integer.toBinaryString(b >>> 2));
    }
}
