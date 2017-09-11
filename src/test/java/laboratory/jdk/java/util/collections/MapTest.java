package laboratory.jdk.java.util.collections;

import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class MapTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MapTest.class);

	@Test
	public void shouldBeNull() {
		HashMap<String, String> map = new HashMap<>();
		assertNull(map.get("야"));
	}

	@Test
	public void hashMap() {
		Map<String, Object> map = new HashMap<>();
		Assert.assertNull(map.get("not-exist-key"));
	}

	@Test
	public void tree() {
		TreeMap<String, String> tree = new TreeMap<>();
		Assert.assertNotNull(tree);
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
}
