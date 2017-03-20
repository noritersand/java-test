package test.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollectionsTest {
//	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(CollectionsTest.class);

	@Test
	public void testForEachLambda() {
		// LIST
		Integer[] values = { 1, 3, 7 };
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(values));
		list.forEach(k -> {
			log.debug(String.valueOf(k));
		});

		// MAP
		Map<String, Integer> items = new HashMap<>();
		items.put("A", 10);
		items.put("B", 20);
		items.forEach((k, v) -> {
			log.debug("Item : " + k + " Count : " + v);
		});
	}

	@Test
	public void testRemoveifLambda() {
		Integer[] values = { 1, 3, 7 };
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(values));
		list.removeIf(p -> p == 3);
		Assert.assertEquals("[1, 7]", list.toString());
	}

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

	@Test
	public void testHashMap() {
		Map<String, Object> map = new HashMap<>();
		Assert.assertNull(map.get("not-exist-key"));
	}

	@Test
	public void testUnchecked() {
		// TODO 어떻게 하면 경고 뜨더라?

		List<String> list = new LinkedList<>();
		list.add("야");
		List<?> uncheckedList = list;
		System.out.println(uncheckedList.get(0));
		String a = (String) uncheckedList.get(0);
		System.out.println(a);

		// TODO 이렇게
//		String[] strs = { "a", "b", "c", "d", "e" };
//		ArrayList<String> list2 = (ArrayList) Arrays.asList(strs);
	}

}
