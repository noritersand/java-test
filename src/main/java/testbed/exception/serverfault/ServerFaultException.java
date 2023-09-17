package testbed.exception.serverfault;

/**
 * <p>서버 오류를 의미하는 예외 클래스</p>
 * <p>개발자 혹은 서버가 잘못한 경우이므로 500 에러로 응답해야함</p>
 * 
 * @author fixalot
 * @since 2023-03-31
 */
public class ServerFaultException extends RuntimeException {

    public ServerFaultException() {
    }

    public ServerFaultException(String message) {
        super(message);
    }

    public ServerFaultException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerFaultException(Throwable cause) {
        super(cause);
    }
}
