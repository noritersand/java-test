package lab.exception.serverfault;

/**
 * <p>데이터 insert/update/delete가 실패했을 때 사용</p>
 *
 * @author fixalot
 * @since 2022-11-15
 */
public class DataUpdateFailureException extends InappropriateStateException {

    public DataUpdateFailureException() {
    }

    public DataUpdateFailureException(String message) {
        super(message);
    }

}
