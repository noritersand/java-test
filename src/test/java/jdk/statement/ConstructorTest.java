package jdk.statement;

import lombok.Getter;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ConstructorTest {

    @Test
    void testInitialize() {
        SomeType a = new SomeType("111");
        assertThat(a.getField1()).isEqualTo("111");
        assertThat(a.getField2()).isEqualTo(null);

        SomeType b = new SomeType("222", "333");
        assertThat(b.getField1()).isEqualTo("222");
        assertThat(b.getField2()).isEqualTo("333");
    }
}

@Getter
class SomeType {
    private final String field1;
    private final String field2;

    SomeType(String field1) {
        this(field1, null);
    }

    SomeType(String field1, String field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

}
