package testbed.servlet.core.finder;

/**
 * methodFinder에서 적당한 메서드를 찾지 못하면 발생하는 익셉션
 *
 * @author fixalot
 * @since 2017-08-09
 */
public class NotFoundMappingException extends RuntimeException {

    public NotFoundMappingException() {
    }

    public NotFoundMappingException(String message) {
        super(message);
    }

    public NotFoundMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundMappingException(Throwable cause) {
        super(cause);
    }
}
