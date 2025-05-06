package builtin.statement;

import org.junit.jupiter.api.Test;

/**
 * assert 사용방법 테스트
 * <p>
 * JUnit을 실행하거나, JVM 옵션에 -ea를 붙여주면 작동함
 *
 * @author fixalot
 * @since 2017-07-27
 */
class JavaAssertionTest {
    public static void main(String[] args) {
        int sum = 20;
        try {
            assert 21 == sum : "sum은 21이어야 함";
        } catch (AssertionError e) {
            System.out.println("Assertion 실패 메시지: " + e.getMessage());
        }
    }

    @Test
    void test() {
        /*
         * assert 비교연산식 : "좌변이 틀렸을 때 던질 AssertionError의 detailMessage";
         */
        int age = -1;
        try {
            assert 0 < age : "나이는 음수가 될 수 없습니다:" + age;
        } catch (Error e) {
            System.out.println("assertion fail: " + e);
        }
    }
}
