package builtin.statement;

import org.junit.jupiter.api.Test;

/**
 * <p>assert 사용방법 테스트
 * <p>테스트 런 일 때만 AssertionError를 발생시키는 방식으로 작동하는 것 같다.
 *
 * @author fixalot
 * @since 2017-07-27
 */
class JavaAssertionTest {
    public static void main(String[] args) {
        int i = -1;
        assert 0 < i;

        int sum = 0;
        while (6 >= i) {
            sum += i;
            i++;
        }
        assert 1 == sum : "엥";
        try {
            assert 21 == sum : "어케쓰는거지";
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void test() {

        /*
         * assert 비교연산식 : "좌변이 틀렸을 때 던질 AssertionError의 detailMessage";
         */

        int a = 1;
        int b = 2;

        assert a != b;

        int i = -1;
        assert 0 > i;

        String pageNo = "abc";
        assert (!"".equals(pageNo));

        int age = -1;
        try {
            assert 0 < age : "나이는 음수가 될 수 없습니다:" + age;
        } catch (Error e) {
            System.out.println("assertion fail: " + e);
        }
    }
}
