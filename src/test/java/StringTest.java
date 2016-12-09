import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringTest {
	@Test
	public void testDomainSplit() {
		assertEquals(1, "localhost".split("\\.").length);
		assertEquals(3, "master.benecafe.com".split("\\.").length);
		assertEquals(2, "daum.net".split("\\.").length);
	}
}

