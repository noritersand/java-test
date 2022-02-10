package misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CopilotTest {
    private static final Logger logger = LoggerFactory.getLogger(CopilotTest.class);

    /**
     * 단축키:
     * - 코파일럿 발동: alt + \
     * - 코파일럿 제안 선택: tab
     * - 자동 완성 제안 창 보기: Find Action -> Open GitHub Copilot
     */
    public static void main(String[] args) {
        printGugudan();
    }

    /**
     * 구구단 출력
     */
    private static void printGugudan() {
        for (int i = 2; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                logger.info("{} * {} = {}", i, j, i * j);
            }
        }
    }
}
