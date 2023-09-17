package testbed.exception.serverfault;

/**
 * 필요한 파라미터가 없음을 의미하는 exception
 *
 * @author fixalot
 * @since 2022-08-17
 */
public class MissingRequiredArgumentException extends InappropriateArgumentException {

    public MissingRequiredArgumentException() {
    }

    public MissingRequiredArgumentException(String message) {
        super(message);
    }

    public MissingRequiredArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingRequiredArgumentException(Throwable cause) {
        super(cause);
    }

}
