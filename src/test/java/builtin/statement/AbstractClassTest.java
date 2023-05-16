package builtin.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class AbstractClassTest {

    @Test
    void shouldBeCompileError() {
//		ExtendMe extendMe = new ExtendMe(); // 추상 클래스는 인스턴스 생성 불가 Cannot instantiate the type ExtendMe
    }

    @Test
    void shouldBeNotNull() {
        ExtendYou extendYou = new ExtendYou();
        assertNotNull(extendYou);
    }
}

abstract class ExtendMe {
    public String getText() {
        return "Yo";
    }

    public abstract String getText2();

    abstract String getText3();

    protected abstract String getText4();
}

class ExtendYou extends ExtendMe {

    @Override
    public String getText2() {
        return null;
    }

    @Override
    String getText3() {
        return null;
    }

    @Override
    protected String getText4() {
        return null;
    }

}
