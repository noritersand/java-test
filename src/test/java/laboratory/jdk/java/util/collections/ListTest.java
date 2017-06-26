package laboratory.jdk.java.util.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ListTest.class);

	@SuppressWarnings("unused")
	private class ListTestModel {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		public ListTestModel(String name) {
			this.name = name;
		}
	}

	@Test
	public void wrongWayClone() {
		ArrayList<ListTestModel> origins = new ArrayList<>();
		origins.add(new ListTestModel("123"));
		origins.add(new ListTestModel("456"));
		origins.add(new ListTestModel("789"));

		// 이건 클론이 아니라 그냥 복사. 같은 인스턴스를 사용한다.
		ArrayList<ListTestModel> newbies = new ArrayList<>(origins);

		Assert.assertEquals(origins.get(0), newbies.get(0));
		Assert.assertTrue(origins.get(0) == newbies.get(0));
	}
	
	@Test
	public void cloneManual() {
		ArrayList<ListTestModel> origins = new ArrayList<>();
		origins.add(new ListTestModel("123"));
		origins.add(new ListTestModel("456"));
		origins.add(new ListTestModel("789"));
		
		ArrayList<ListTestModel> newbies = new ArrayList<>();
		for (ListTestModel ele : origins) {
			newbies.add(new ListTestModel(ele.getName()));
		}

		Assert.assertNotEquals(origins.get(0), newbies.get(0));
		Assert.assertTrue(origins.get(0) != newbies.get(0));
	}

	@Test
	public void getSize() {
		ArrayList<String> stringList = new ArrayList<>();
		Assert.assertEquals(0, stringList.size());
		stringList = new ArrayList<>(10); // 리스트의 capacity를 지정한다. size가 아니다.
		Assert.assertEquals(0, stringList.size()); // capacity 지정과 size는 관련 없음.
	}

	@Test
	public void removeElement() {
		ArrayList<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));
		Iterator<String> iter = list.iterator();
		while (iter.hasNext()) {
			String s = iter.next();

			if (s.equals("a")) {
				iter.remove();
			}
		}
	}

	@Test
	public void find() {
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
	public void toArray() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(123);
		list.add(234);
		list.add(345);
		Assert.assertArrayEquals(new Integer[] { 123, 234, 345 }, list.toArray(new Integer[list.size()]));
	}

	@Test
	public void fromArray() {
		Integer[] values = { 1, 3, 7 };
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(values));
		Assert.assertEquals("[1, 3, 7]", list.toString());
	}

	@Test
	public void fromArrayWithStream() {
		String[] strs = { "a", "b", "c", "d", "e" };
		List<String> stringList = Arrays.stream(strs).collect(Collectors.toList());
		Assert.assertEquals("[a, b, c, d, e]", stringList.toString());

		int[] spam = new int[] { 1, 2, 3 };
		List<Integer> integerList = Arrays.stream(spam).boxed().collect(Collectors.toList());
		Assert.assertEquals("[1, 2, 3]", integerList.toString());
	}

	@Test
	public void clear() {
		ArrayList<Integer> list = new ArrayList<>();
		Assert.assertNotNull(list);
		list.clear();
		Assert.assertNotNull(list);
	}

	@Test
	public void arrayList() {
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
