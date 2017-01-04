import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

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
	
	@Test
	public void testRelative2() {
		Path source = new File("webapp\\upload\\temp\\").toPath();
		Path target = new File("webapp\\upload\\temp\\201612\\28201838255.png").toPath();
		Assert.assertEquals("201612\\28201838255.png", source.relativize(target).toString()); // dev에서 sso로 가려면 'cd ..\..\sso'
	}
	
	@Test
	public void testResolve() throws IOException {
		Path dev = new File("c:\\dev\\git").toPath();
		Path someFile = dev.resolve("someFile");
		Assert.assertEquals("c:\\dev\\git\\someFile", someFile.toString());
	}

	@Test
	public void testNewInstnc() {
		Path path1 = Paths.get(URI.create("file://C:/project/workspace"));
		Path path2 = Paths.get("C:\\project\\workspace");
		Path path3 = Paths.get("localhost/upload");
		Path path4 = Paths.get("/localhost/upload");
		
		System.out.println(path1);
		System.out.println(path2);
		System.out.println(path3);
		System.out.println(path4);
	}
}
