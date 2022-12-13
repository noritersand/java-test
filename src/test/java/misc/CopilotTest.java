package misc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum CopilotTest {
    ;

    /**
     * <b>단축키:
     * <li>코파일럿 발동: alt + \
     * <li>코파일럿 제안 선택: tab
     * <li>자동 완성 제안 창 보기: Find Action -> Open GitHub Copilot
     */
    public static void main(String[] args) {
        printGugudan();
    }

    /**
     * 구구단 출력
     */
    private static void printGugudan() {
        for (int i = 2; 10 > i; i++) {
            for (int j = 1; 10 > j; j++) {
                log.info("{} * {} = {}", i, j, i * j);
            }
        }
    }
}
