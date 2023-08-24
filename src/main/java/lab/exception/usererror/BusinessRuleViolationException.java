package lab.exception.usererror;

/**
 * <p>업무적으로 허용되지 않은 처리 시도를 의미하는 예외</p>
 * <p>ServerFaultException과 다르게 사용자 잘못이므로 200으로 응답해야 함</p>
 *
 * @author fixalot
 * @since 2022-06-15
 */
public class BusinessRuleViolationException extends UserErrorException {

    public BusinessRuleViolationException() {}

    public BusinessRuleViolationException(String message) {
        super(message);
    }

    public BusinessRuleViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessRuleViolationException(Throwable cause) {
        super(cause);
    }
}
