package builtin.statement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author fixalot
 * @since 2017-07-27
 */
class ReferenceCopyTest {
    @Test
    void avoidReassigningParameters() {
        Vo vo = new Vo();
        assertEquals(0, vo.getNum());
        copyParam(vo);
        assertNotEquals(0, vo.getNum());
    }

    /**
     * 객체 참조 변수를 복사해봐야 아무 의미없는데 왜 PMD에선 중요도2씩이나 줘서 이렇게 하라는지 모르겠네.
     *
     * @param vo
     * @author fixalot
     */
    private void copyParam(Vo vo) {
        Vo vo2 = vo;
        vo2.setNum(123);
    }

    @Test
    void test() {
        String str = "a";
        doSomething(str);
        assertNotEquals("b", str);

        int num = 1;
        doSomething(num);
        assertNotEquals(2, num);

        Integer number = 1;
        doSomething(number);
        assertNotEquals(Integer.valueOf(2), number);

        Vo vo = new Vo();
        vo.setStr("a");
        vo.setNum(1);
        doSomething(vo);
        assertEquals("b", vo.getStr());
        assertEquals(2, vo.getNum());
    }

    private void doSomething(Vo vo) {
        vo.setStr("b");
        vo.setNum(2);
    }

    private void doSomething(String obj) {
        obj = "b"; // obj = new String("b")
    }

    private void doSomething(int obj) {
        obj = 2;
    }

    private void doSomething(Integer obj) {
        obj = 2; // new Integer(2);
    }

}

class Vo {
    private String str;
    private int num;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
