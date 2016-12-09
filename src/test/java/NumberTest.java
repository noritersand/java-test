import org.junit.Assert;
import org.junit.Test;

public class NumberTest {
	@Test
	public void testAutoBoxing() {
		Assert.assertEquals(1, (long) new Long(1));
	}
}
