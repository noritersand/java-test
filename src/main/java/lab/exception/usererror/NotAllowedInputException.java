package lab.exception.usererror;

/**
 * <p>사용자 입력값이 업무적 규칙을 위배할 때 사용하는 exception</p>
 *
 * @author fixalot
 * @since 2023-04-04
 */
public class NotAllowedInputException extends UserErrorException {

    public NotAllowedInputException() {}

    public NotAllowedInputException(String message) {
        super(message);
    }

    public NotAllowedInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAllowedInputException(Throwable cause) {
        super(cause);
    }
}
