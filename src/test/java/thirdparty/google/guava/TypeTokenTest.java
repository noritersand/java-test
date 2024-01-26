package thirdparty.google.guava;

import com.google.common.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class TypeTokenTest {

    @Test
    void useTypeToken() {
        @SuppressWarnings("serial")
        Type listType = new TypeToken<ArrayList<MyClass>>() {
        }.getType();
        assertEquals("java.util.ArrayList<thirdparty.google.guava.MyClass>", String.valueOf(listType));
    }
}

class MyClass {

}
