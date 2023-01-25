package lab.exception.rule;

/**
 * 업무적으로 허용되지 않은 처리 시도를 의미하는 예외
 *
 * @author fixalot
 * @since 2022-06-15
 */
public class BusinessRuleViolationException extends RuntimeException {

    public BusinessRuleViolationException() {}

    public BusinessRuleViolationException(String message) {
        super(message);
    }
    
}
