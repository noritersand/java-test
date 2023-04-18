package lab.exception.serverfault;

/**
 * 필요한 데이터(보통은 데이터베이스의 데이터)가 없음을 의미하는 exception
 *
 * @author fixalot
 * @since 2022-11-25
 */
public class MissingRequiredDataException extends InappropriateStateException {

    public MissingRequiredDataException() {
    }

    public MissingRequiredDataException(String message) {
        super(message);
    }

    public MissingRequiredDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingRequiredDataException(Throwable cause) {
        super(cause);
    }
}
