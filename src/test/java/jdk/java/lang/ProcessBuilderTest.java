package jdk.java.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class ProcessBuilderTest {

    @Test
    void executeFile() throws IOException {
        ProcessBuilder pb = new ProcessBuilder("src/test/resources/process-builder-test/test.bat");
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT); // 얘네 안하면 콘솔 출력 안됨
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);
        pb.start();
    }

    @Test
    void executeWindowCommand() throws IOException {
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "cd \"C:\\dev\" && dir");
//		ProcessBuilder pb = new ProcessBuilder("csh", "-c", "ls"); // for unix-like
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT); // 얘네 안하면 콘솔 출력 안됨
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);
        pb.start();
    }
}
