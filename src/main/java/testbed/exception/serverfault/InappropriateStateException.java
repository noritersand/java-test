package testbed.exception.serverfault;

/**
 * 뭔가 현재 상태가 이상하면 사용
 *
 * @author fixalot
 * @since 2022-11-25
 */
public class InappropriateStateException extends ServerFaultException {

    public InappropriateStateException() {}

    public InappropriateStateException(String message) {
        super(message);
    }

    public InappropriateStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public InappropriateStateException(Throwable cause) {
        super(cause);
    }
}
