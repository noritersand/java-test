import java.io.File;
import java.nio.file.Path;

import org.junit.Assert;
import org.junit.Test;

public class PathTest {
	@Test
	public void testRelative() {
		Path dev = new File("c:\\dev\\git").toPath();
		Path sso = new File("c:\\sso").toPath();
		Assert.assertEquals("..\\..\\sso", dev.relativize(sso).toString()); // dev에서 sso로 가려면 'cd ..\..\sso'
		Assert.assertEquals("..\\dev\\git", sso.relativize(dev).toString()); // sso에서 dev로 가려면 'cd ..\dev\git'
		Assert.assertEquals("", dev.relativize(dev).toString()); // dev에서 dev로 가려면 'cd .'
	}
}
