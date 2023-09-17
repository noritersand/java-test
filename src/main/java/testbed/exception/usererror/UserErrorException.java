package testbed.exception.usererror;

/**
 * <p>사용자가 잘못한 경우를 나타내는 예외 클래스</p>
 * 
 * @author fixalot
 * @since 2023-07-10
 */
public class UserErrorException extends RuntimeException {

    public UserErrorException() {
    }

    public UserErrorException(String message) {
        super(message);
    }

    public UserErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserErrorException(Throwable cause) {
        super(cause);
    }
}
