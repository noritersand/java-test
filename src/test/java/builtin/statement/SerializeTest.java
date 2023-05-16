package builtin.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 내부 클래스를 시리얼라이즈 하려면 외부 클래스도 시리얼라이즈 가능해야함
 */
@Slf4j
public class SerializeTest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Test
    void deserialize() throws Exception {
        File file = Paths.get("src/test/resources/serialize-test/test.ser").toFile();

        // some instance
        SerializeMe instance = new SerializeMe();
        instance.setValue("hello");

        // serialize
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file, false));
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(instance);
        bos.close();
        oos.close();

        // deserialize
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        ObjectInputStream ois = new ObjectInputStream(bis);
        SerializeMe obj = (SerializeMe) ois.readObject();
        ois.close();
        bis.close();

        // assert
        assertEquals("hello", obj.getValue());
    }

    private class SerializeMe implements Serializable {
        private static final long serialVersionUID = 6120061019387055537L;
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
