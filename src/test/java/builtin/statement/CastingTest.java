package builtin.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * 캐스팅 테스트
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class CastingTest {

    /**
     * 다른 타입으로 변환하기
     */
    @Test
    void typeCasting() {
        int intV = 1;
//        Double doubleValue = integerValue; // 이 코드는 컴파일 에러가 발생함
        double doubV = intV; // 캐스팅 표현식 없이 타입 캐스팅 가능
        assertThat(doubV).isEqualTo(1.0);

        double doubV2 = 5.5;
        int intV2 = (int) doubV2; // 이 경우엔 생략할 수 없음
        assertThat(intV2).isEqualTo(5);
    }

    /**
     * 타입 캐스팅 하려는 값이 null이라도 런타임 에러는 발생하지 않음.
     */
    @Test
    void castWithNull() {
        Object imnotnotnull = null;
        String string = (String) imnotnotnull;
        assertThat(string).isNull();

        Integer integer = (Integer) imnotnotnull;
        assertThat(integer).isNull();
    }

    @Test
    void upCasting() {
        Child c = new Child();
        Parent p = c;
        assertThat(p.getName()).isEqualTo("Child");
//		p.play(); // undefined method


    }

    @Test
    void downCasting() {
        Child c1 = new Child();
        c1.setValue(65536);
        Parent p1 = (Parent) c1;
//        p1.getValue(); // Cannot resolve method 'getValue' in 'Parent'
        // 부모는 없는 getValue() 메서드는 가려진 상태가 됨. 그렇다고 인스턴스 내부의 값이 사라지는 건 아님
        // 그래서 다운캐스팅을 다시 하면
        Child c2 = (Child) p1;
        assertThat(c2.getValue()).isEqualTo(65536);

        // 요로케는 안됨.
        assertThatThrownBy(() -> {
            Parent p = new Parent();
            @SuppressWarnings("unused")
            Child c = (Child) p;
        }).isInstanceOf(ClassCastException.class).hasMessage("class builtin.statement.CastingTest$Parent cannot be cast " +
                "to class builtin.statement.CastingTest$Child (builtin.statement.CastingTest$Parent and builtin.statement.CastingTest$Child are in unnamed module of loader 'app')");

        // 요로케는 됨.
        Parent p = new Child();
        Child c = (Child) p;

        assertThat(c.getName()).isEqualTo("Child");
        c.play();
    }

    private class Parent {
        public String getName() {
            return "Parent";
        }

    }

    private class Child extends Parent {
        private int value;

        public void play() {
            log.debug("uoo-hoo!");
        }
        @Override
        public String getName() {
            return "Child";
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
