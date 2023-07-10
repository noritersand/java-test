package lab.exception.usererror;

/**
 * <p>참조 제약이 있음을 의미하는 exception.
 * <p>참조 제약이 꼭 DBMS의 외래 키 제약을 의미하는 것은 아니고, 업무적인 제약일 수도 있음.
 *
 * @author fixalot
 * @since 2022-05-31
 */
public class ReferenceConstraintException extends UserErrorException {

    public ReferenceConstraintException() {}

    public ReferenceConstraintException(String message) {
        super(message);
    }

}
