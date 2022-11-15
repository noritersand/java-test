package lab;

/**
 * 데이터 insert/update/delete가 실패했을 때 사용
 *
 * @author fixalot
 * @since 2022-11-15
 */
public class DataUpdateFailureException extends BusinessRuleViolationException {

    public DataUpdateFailureException() {}

    public DataUpdateFailureException(String message) {
        super(message);
    }

}
