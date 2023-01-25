package lab.exception.wrong;

/**
 * 뭔가 현재 상태가 이상하면 사용
 *
 * @author fixalot
 * @since 2022-11-25
 */
public class InappropriateStateException extends RuntimeException {

    public InappropriateStateException() {}

    public InappropriateStateException(String message) {
        super(message);
    }

}
