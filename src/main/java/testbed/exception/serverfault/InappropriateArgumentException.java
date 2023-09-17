package testbed.exception.serverfault;

/**
 * 메서드 전달인자가 잘못됐음을 의미하는 exception
 *
 * @author fixalot
 * @since 2022-11-15
 */
public class InappropriateArgumentException extends ServerFaultException {

    public InappropriateArgumentException() {
    }

    public InappropriateArgumentException(String message) {
        super(message);
    }

    public InappropriateArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InappropriateArgumentException(Throwable cause) {
        super(cause);
    }

}
