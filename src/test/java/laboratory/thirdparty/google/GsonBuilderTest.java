package laboratory.thirdparty.google;

import org.junit.Assert;
import org.junit.Test;

import laboratory.util.json.GsonBuilder;

/**
 * 커스텀 TypeAdeptor가 선언된 GsonBuilder 테스트
 *  
 * @author fixalot
 * @since 2017-12-01
 */
public class GsonBuilderTest {
	@Test
	public void test() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Assert.assertNotNull(gsonBuilder);
	}
}
