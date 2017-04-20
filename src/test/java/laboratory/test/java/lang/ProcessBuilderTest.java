package laboratory.test.java.lang;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessBuilderTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(ProcessBuilderTest.class);
	
	@Test
	public void test() throws IOException {
		ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "cd \"C:\\dev\" && dir");
		pb.redirectOutput(Redirect.INHERIT); // 얘네 안하면 콘솔 출력 안됨
		pb.redirectError(Redirect.INHERIT);
		pb.start();
	}
}
