package laboratory.mascaradee.service;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class MyServiceImpl implements MyService {

	@Override
	public void executeMe() {
		try {
			@SuppressWarnings("unused")
			int nan = 1 / 0; // 익셉션 발생
		} catch (Exception e) {
			throw new YourException("연금!");
		}
	}
}
