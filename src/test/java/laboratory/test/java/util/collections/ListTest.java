package laboratory.test.java.util.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(ListTest.class);

	@Test
	public void testFind() {
		Integer[] values = { 1, 3, 7 };
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(values));
		final int targetValue = 3;
		int targetIndex = 99;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == targetValue) {
				targetIndex = i;
				break;
			}
		}
		Assert.assertEquals(1, targetIndex);
	}

	@Test
	public void testToArray() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(123);
		list.add(234);
		list.add(345);
		Assert.assertArrayEquals(new Integer[] { 123, 234, 345 }, list.toArray(new Integer[list.size()]));
	}

	@Test
	public void testFromArray() {
		Integer[] values = { 1, 3, 7 };
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(values));
		Assert.assertEquals("[1, 3, 7]", list.toString());
	}

	@Test
	public void testFromArrayUsingStream() {
		String[] strs = { "a", "b", "c", "d", "e" };
		List<String> stringList = Arrays.stream(strs).collect(Collectors.toList());
		Assert.assertEquals("[a, b, c, d, e]", stringList.toString());

		int[] spam = new int[] { 1, 2, 3 };
		List<Integer> integerList = Arrays.stream(spam).boxed().collect(Collectors.toList());
		Assert.assertEquals("[1, 2, 3]", integerList.toString());
	}

	@Test
	public void testClear() {
		ArrayList<Integer> list = new ArrayList<>();
		Assert.assertNotNull(list);
		list.clear();
		Assert.assertNotNull(list);
	}

	@Test
	public void testArrayList() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(9);
		list.add(8);
		list.add(7);
		list.add(6);
		list.add(5);
		list.add(4);
		list.add(3);
		list.add(2);
		list.add(1);

		list.remove(1);

		Assert.assertEquals("[9, 7, 6, 5, 4, 3, 2, 1]", list.toString());
	}
}
