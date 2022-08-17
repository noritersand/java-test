package jdk.statement;

import jdk.statement.EnumTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum ImSpecial {
    // enum은 값(=상수)별 클래스 본문을 가질 수 있음. (value-specific class bodies)
    A {
        @Override
        public String destroySelf() {
            return "I'm dying :<";
        }
    },

    B {
        @Override
        public String destroySelf() {
            return "Noooooooooo...";
        }
    };

    public String destroySelf() {
        return this.destroySelf();
    }
}
