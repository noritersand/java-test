package laboratory.jdk.java.nio;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class FilesTest {
	@Test
	public void test() {
		Assert.assertTrue(Files.isReadable(Paths.get("c:")));
		Assert.assertFalse(Files.isReadable(Paths.get("m:")));
	}
}
