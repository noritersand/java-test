package jdk.java.lang;

import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * StringBuffer 테스트 유닛
 * 
 * @since 2018-09-28
 * @author fixal
 */
public class StringBufferTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(StringBufferTest.class);

	/**
	 * 한글을 3바이트로 처리하는 UTF-8의 바이트배열을 4바이트씩 잘라서 String으로 만들면 한글이 망가지는데,
	 * StringBuffer를 써도 마찬가지인지 확인하는 테스트 케이스
	 * 
	 * @author fixal
	 */
	@Test
	public void concatBytes() {
		final String origin = "가나다라";
		final byte[] bytes = origin.getBytes(StandardCharsets.UTF_8);
		Assert.assertArrayEquals(new byte[] { -22, -80, -128, -21, -126, -104, -21, -117, -92, -21, -99, -68 }, bytes);
		
		Assert.assertEquals("가", new String(bytes, 0, 3, StandardCharsets.UTF_8));
		Assert.assertEquals("나", new String(bytes, 3, 3, StandardCharsets.UTF_8));
		Assert.assertEquals("다", new String(bytes, 6, 3, StandardCharsets.UTF_8));
		Assert.assertEquals("라", new String(bytes, 9, 3, StandardCharsets.UTF_8));
		
		String a, b, c;
		Assert.assertEquals("가�", a = new String(bytes, 0, 4, StandardCharsets.UTF_8));
		Assert.assertEquals("���", b = new String(bytes, 4, 4, StandardCharsets.UTF_8));
		Assert.assertEquals("�라", c = new String(bytes, 8, 4, StandardCharsets.UTF_8));
		StringBuffer buffer = new StringBuffer().append(a).append(b).append(c);
		
		Assert.assertEquals("가�����라", buffer.toString());
	}
}
