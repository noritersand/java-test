package laboratory.mascaradee.service;

public interface MyService {
	public void executeMe();
	
	public class YourException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public YourException(String message) {
			super(message);
		}		
	}
}
