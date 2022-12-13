package jdk.java.lang;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fixalot
 * @since 2017-07-27
 */
public class ProcessBuilderTest {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ProcessBuilderTest.class);

    @Test
    public void executeFile() throws IOException {
        ProcessBuilder pb = new ProcessBuilder("src/test/resources/process-builder-test/test.bat");
        pb.redirectOutput(Redirect.INHERIT); // 얘네 안하면 콘솔 출력 안됨
        pb.redirectError(Redirect.INHERIT);
        pb.start();
    }

    @Test
    public void executeWindowCommand() throws IOException {
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "cd \"C:\\dev\" && dir");
//		ProcessBuilder pb = new ProcessBuilder("csh", "-c", "ls"); // for unix-like
        pb.redirectOutput(Redirect.INHERIT); // 얘네 안하면 콘솔 출력 안됨
        pb.redirectError(Redirect.INHERIT);
        pb.start();
    }
}
