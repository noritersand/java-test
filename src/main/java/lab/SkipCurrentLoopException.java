package lab;

/**
 * 예외이긴 한데 단순히 루프 continue로 사용할 때 필요한 클래스
 *
 * @author fixalot
 * @since 2022-11-15
 */
public class SkipCurrentLoopException extends BusinessRuleViolationException {

    public SkipCurrentLoopException() {
    }

    public SkipCurrentLoopException(String message) {
        super(message);
    }
}
