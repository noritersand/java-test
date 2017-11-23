package laboratory.jdk18;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
// 내부 클래스를 시리얼라이즈히랴먄 외부 클래스도 시리얼라이즈 가능해야함
public class SerializeTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SerializeTest.class);

	@Test
	public void deserialize() throws ClassNotFoundException, IOException {
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
		Assert.assertEquals("hello", obj.getValue());
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