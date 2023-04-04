package lab.exception.usererror;

/**
 * <p>사용자가 입력한 전달인자가 업무적 규칙을 위배할 때 사용하는 exception</p>
 *
 * @author fixalot
 * @since 2023-04-04
 */
public class NotAllowedArgumentException extends BusinessRuleViolationException {

    public NotAllowedArgumentException() {}

    public NotAllowedArgumentException(String message) {
        super(message);
    }

}
