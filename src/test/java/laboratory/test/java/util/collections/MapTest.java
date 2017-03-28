package laboratory.test.java.util.collections;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(MapTest.class);

	@Test
	public void testMap() {
		HashMap<String, String> map = new HashMap<>();
		assertNull(map.get("야"));
	}

	@Test
	public void testHashMap() {
		Map<String, Object> map = new HashMap<>();
		Assert.assertNull(map.get("not-exist-key"));
	}

	@Test
	public void testTree() {
		TreeMap<String, String> tree = new TreeMap<>();
		Assert.assertNotNull(tree);
	}

}
