package laboratory.mascaradee.action;

import laboratory.mascaradee.service.MyService;
import laboratory.mascaradee.service.MyService.YourException;
import laboratory.mascaradee.service.MyServiceImpl;

public class MyAction {
	public static void main(String[] args) {
		MyService service = new MyServiceImpl();
		try {
			service.executeMe();
		} catch (YourException e) {
			System.out.println(e.getMessage());
		}
	}
}
