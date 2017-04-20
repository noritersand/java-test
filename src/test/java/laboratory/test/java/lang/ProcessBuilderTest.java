package laboratory.test.java.lang;

import java.io.IOException;
import java.io.OutputStreamWriter;
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
		Process p = pb.start();
//		OutputStreamWriter writer = new OutputStreamWriter(p.getOutputStream(), Charset.forName("ISO-8859-1")); 
		// writer가 사용할 케릭터셋을 ISO-8859-1로 하든, UTF-8로 하든, 아예 안하든 결과는 같음.
		OutputStreamWriter writer = new OutputStreamWriter(p.getOutputStream());
		writer.flush();
	}
}
