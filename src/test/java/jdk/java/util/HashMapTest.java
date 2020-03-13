package jdk.java.util;

import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link HashMap} 테스트 유닛
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class HashMapTest {
	private static final Logger logger = LoggerFactory.getLogger(HashMapTest.class);

	/**
	 * 해시맵은 내부의 값이 같으면 동등하다고 판단함.
	 * 
	 * @author noritersand
	 */
	@Test
	public void testEquals() {
		HashMap<String, Object> x = new HashMap<>();
		HashMap<String, Object> y = new HashMap<>();
		x.put("a", "b");
		y.put("a", "b");
		Assert.assertTrue(x != y); // 인스턴스는 다르지만
		Assert.assertTrue(x.hashCode() == y.hashCode()); // AbstractMap.hashCode()에서 반환하는 값은 같고
		Assert.assertTrue(x.equals(y)); // AbstractMap.equals()는 둘이 동등하다고 판단함.
	}
	
	@Test
	public void testEntry() {
		HashMap<String, String> map = new HashMap<>();
		map.put("first", "1st");
		map.put("second", "2nd");
		map.put("third", "3rd");

		Set<Entry<String, String>> entry = map.entrySet();
		for (Entry<String, String> ele : entry) {
			String key = ele.getKey();
			String value = ele.getValue();
			logger.debug("key: {}, value: {}", key, value);
		}
	}
	
	@Test
	public void shouldBeNull() {
		HashMap<String, String> map = new HashMap<>();
		assertNull(map.get("야"));
	}

	@Test
	public void checkNPE() {
		Map<String, Object> map = new HashMap<>();
		final String empty = (String) map.get("empty");
		Assert.assertNull(empty);
	}
	
	/**
	 * 맵도 반복문을 돌려서 꺼낼 수 있다.
	 * 왜냐믄 맵의 키셋에 iterator가 있기 때문
	 * 
	 * @author fixalot
	 */
	@Test
	public void possibleLoopStatement() {
		Map<String, Object> map = new HashMap<>();
		map.put("a", 1234);
		map.put("b", 5678);
		map.put("c", 90);
		
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			Assert.assertNotNull(map.get(key));
		}
	}
	
	@Test
	public void testForEach() {
		Map<String, Integer> items = new HashMap<>();
		items.put("A", 10);
		items.put("B", 20);
		items.forEach((k, v) -> {
			logger.debug("Item : " + k + " Count : " + v);
		});
	}
}
