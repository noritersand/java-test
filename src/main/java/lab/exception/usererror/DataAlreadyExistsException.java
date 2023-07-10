package lab.exception.usererror;

/**
 * 데이터가 이미 있음을 의미하는 exception
 *
 * @author fixalot
 * @since 2022-05-09
 */
public class DataAlreadyExistsException extends UserErrorException {

    public DataAlreadyExistsException() {}

    public DataAlreadyExistsException(String message) {
        super(message);
    }

}
