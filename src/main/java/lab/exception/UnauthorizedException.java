package lab.exception;

/**
 * 요청한 사용자에게 권한이 없음을 의미하는 exception
 *
 * @author betoru
 * @since 2023-01-25
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {}

    public UnauthorizedException(String message) {
        super(message);
    }

}
