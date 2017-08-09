package laboratory.servlet.mvc.finder;

/**
 * methodFinder에서 적당한 메서드를 찾지 못하면 발생하는 익셉션
 * 
 * @since 2017-08-09
 * @author fixalot
 */
public class CannotFindMappingException extends RuntimeException {
	private static final long serialVersionUID = 4918349620626683926L;
	
	public CannotFindMappingException() {
		super();
	}
	
	public CannotFindMappingException(String message) {
		super(message);
	}
}
