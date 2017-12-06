package laboratory.thirdparty.google;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import laboratory.util.json.GsonBuilder;

/**
 * 커스텀 TypeAdeptor가 선언된 GsonBuilder 테스트
 *  
 * @author fixalot
 * @since 2017-12-01
 */
public class GsonBuilderTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(GsonBuilderTest.class);
	
	@Test
	public void test() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Assert.assertNotNull(gsonBuilder);
	}
}
