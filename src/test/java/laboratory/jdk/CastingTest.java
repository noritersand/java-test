package laboratory.jdk;


import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastingTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(CastingTest.class);
	
	@Test
	public void test() {
		Character oc = new Character('Y');
		Assert.assertFalse(oc.equals("Y"));
		Assert.assertFalse("Y".equals(oc));
	}
	
	@Test
	public void testAutoCasting() {
		
	}
	
	@Test
	public void testUpCasting() {
		
	}
	
	@Test
	public void testDownCasting() {
		
	}
}
