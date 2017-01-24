package jdk.nio;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class FilesTest {
	@Test
	public void test1() {
		Assert.assertTrue(Files.isReadable(Paths.get("c:")));
		Assert.assertFalse(Files.isReadable(Paths.get("j:")));
	}
}
