package lab;

/**
 * 필요한 파라미터가 없음을 의미하는 exception
 *
 * @author fixalot
 * @since 2022-08-17
 */
public class InappropriateArgumentException extends BusinessRuleViolationException {

    public InappropriateArgumentException() {
    }

    public InappropriateArgumentException(String message) {
        super(message);
    }

}
