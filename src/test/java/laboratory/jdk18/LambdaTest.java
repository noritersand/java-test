package laboratory.jdk18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class LambdaTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(LambdaTest.class);

	@Test
	public void testForEach() {
		// LIST
		Integer[] values = { 1, 3, 7 };
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(values));
		list.forEach(k -> {
			logger.debug(String.valueOf(k));
		});

		// MAP
		Map<String, Integer> items = new HashMap<>();
		items.put("A", 10);
		items.put("B", 20);
		items.forEach((k, v) -> {
			logger.debug("Item : " + k + " Count : " + v);
		});
	}

	@Test
	public void testRemoveif() {
		Integer[] values = { 1, 3, 7 };
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(values));
		list.removeIf(p -> p == 3);
		Assert.assertEquals("[1, 7]", list.toString());
	}
}
