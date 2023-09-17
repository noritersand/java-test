package testbed.exception;

/**
 * 예외이긴 한데 단순히 루프 continue 트리거로 사용할 때 필요한 클래스
 *
 * @author fixalot
 * @since 2022-11-15
 */
public class SkipCurrentLoopException extends RuntimeException {

    public SkipCurrentLoopException() {
    }

    public SkipCurrentLoopException(String message) {
        super(message);
    }

    public SkipCurrentLoopException(String message, Throwable cause) {
        super(message, cause);
    }

    public SkipCurrentLoopException(Throwable cause) {
        super(cause);
    }
}
