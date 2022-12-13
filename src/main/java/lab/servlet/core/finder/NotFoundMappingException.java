package lab.servlet.core.finder;

/**
 * methodFinder에서 적당한 메서드를 찾지 못하면 발생하는 익셉션
 *
 * @author fixalot
 * @since 2017-08-09
 */
public class NotFoundMappingException extends RuntimeException {
    private static final long serialVersionUID = 4918349620626683926L;

    public NotFoundMappingException() {
    }

    public NotFoundMappingException(String message) {
        super(message);
    }
}
