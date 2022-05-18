package jdk.statement;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 람다 표현식 테스트
 * 
 * - https://d2.naver.com/helloworld/4911107
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class LambdaTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(LambdaTest.class);

	/**
	 * 이게 대체 뭐냐
	 * 
	 * @author fixalot
	 */
	@Test
	public void test1() {
		Map<String, Object> goods = new HashMap<>();
		goods.put("adultYn", "1");
		Predicate<Map<String, Object>> isAdultGoods = g -> StringUtils.equals(MapUtils.getString(g, "adultYn"), "1");
		assertTrue(isAdultGoods.test(goods));
	}
}
