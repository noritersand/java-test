import org.junit.Assert;
import org.junit.Test;

public class ClassTest {
	@Test
	public void testClass() throws ClassNotFoundException {
		System.out.println(Class.class);
		System.out.println(new My<String>().getClass());
		
		System.out.println(String.class);
		System.out.println(Class.forName("java.lang.String").getClass());
	}
	
	@Test
	public void testDifferent() {
		Assert.assertNotEquals(String.class, String[].class);
	}
}

class My<T> {
	
}
