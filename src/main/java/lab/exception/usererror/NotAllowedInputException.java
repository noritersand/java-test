package lab.exception.usererror;

/**
 * <p>사용자 입력값이 업무적 규칙을 위배할 때 사용하는 exception</p>
 *
 * @author fixalot
 * @since 2023-04-04
 */
public class NotAllowedInputException extends BusinessRuleViolationException {

    public NotAllowedInputException() {}

    public NotAllowedInputException(String message) {
        super(message);
    }

}