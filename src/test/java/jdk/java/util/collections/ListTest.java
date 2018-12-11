package jdk.java.util.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
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
	public void testAdd() {
		List<Object> list = new ArrayList<>();
		list.add(null);
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

	/**
	 * 리스트 안의 특정 요소 삭제하기 #1
	 * 
	 * @author fixalot
	 */
	@Test
	public void removeElement1() {
		ArrayList<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));
		Iterator<String> iter = list.iterator();
		while (iter.hasNext()) {
			String s = iter.next(); // 반드시 remove() 전에 호출되어야 함.
			if (s.equals("a")) {
				iter.remove();
			}
		}
	}

	/**
	 * 리스트 안의 특정 요소 삭제하기 #2
	 * 
	 * @author fixalot
	 */
	@Test
	public void removeElement2() {
		ArrayList<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));
		for (int i = 0; i < list.size(); i++) {
			final String str = list.get(i);
			if (str.equals("a")) {
				list.remove(i);
				i--;
			}
		}
		Assert.assertEquals(Arrays.asList("b", "c", "d"), list);
		Assert.assertEquals(3, list.size());
	}

	/**
	 * 리스트 안의 특정 요소 삭제하기 #3
	 * 
	 * @author fixalot
	 */
	@Test
	public void removeElement3() {
		String[] strs = { "a", "b", "c", "d", "e" };

		// 앞에서 3개 자르기
		List<String> list = Arrays.stream(strs).collect(Collectors.toList());
//		for (int cnt = 0, i = 0; i < list.size(); i++) {
//			if (cnt < 3) {
//				list.remove(0);
//				i--;
//				cnt++;
//			}
//		} // for 필요 없잖여
		{
			int cnt = 0;
			while (true) {
				if (cnt == 3) {
					break;
				}
				list.remove(0);
				cnt++;
			}
		}
		Assert.assertEquals("[d, e]", list.toString());

		// 뒤에서 3개 자르기
		list = Arrays.stream(strs).collect(Collectors.toList());
		for (int cnt = 0, i = list.size(); i >= 0; i--) {
			if (cnt < 3) {
				list.remove(list.size() - 1);
				i++;
				cnt++;
			}
		}
		Assert.assertEquals("[a, b]", list.toString());
	}

	@Test
	public void removeElementWithIterator() {
		ArrayList<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));
		Iterator<String> iter = list.iterator();
		while (iter.hasNext()) {
			String s = iter.next();
			if (s.equals("a")) {
				iter.remove();
			}
		}
		Assert.assertEquals(Arrays.asList("b", "c", "d"), list);
		Assert.assertEquals(3, list.size());
	}

//	@Test
//	public void removeElementWithStream() {
//		ArrayList<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));
//		list.stream().set
//	}

	/**
	 * 리스트 검색 테스트 1: for문으로 전체 검색
	 */
	@Test
	public void search() {
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

	private ArrayList<HashMap<String, Object>> getSomeList() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		map.put("key", "a");
		map.put("value", "123");
		list.add(map);
		map = new HashMap<>();
		map.put("key", "b");
		map.put("value", "456");
		list.add(map);
		map = new HashMap<>();
		map.put("key", "c");
		map.put("value", "789");
		list.add(map);
		map = new HashMap<>();
		map.put("key", "d");
		map.put("value", "012");
		list.add(map);
		return list;
	}

	/**
	 * 리스트 검색 테스트 2: apache commons의 {@link CollectionUtils} 사용
	 */
	@Test
	public void searchWithApacheCommons() {
		ArrayList<HashMap<String, Object>> list = getSomeList();
		// filter에 사용할 predicate 설정
		Predicate condition = new Predicate() {
			@Override
			public boolean evaluate(Object arg) {
				@SuppressWarnings("unchecked")
				HashMap<String, Object> map = (HashMap<String, Object>) arg;
				String key = (String) map.get("key");
				return "a".equals(key) || "c".equals(key);
			}
		};
		@SuppressWarnings("unchecked")
		ArrayList<HashMap<String, Object>> searchResult = (ArrayList<HashMap<String, Object>>) CollectionUtils.select(list, condition);
		Assert.assertEquals(4, list.size()); // 원래 리스트는 변하지 않음
		Assert.assertEquals(2, searchResult.size()); // filter의 조건에 맞는 새로운 리스트의 길이
		Assert.assertEquals("123", searchResult.get(0).get("value"));
		Assert.assertEquals("789", searchResult.get(1).get("value"));
	}

	/**
	 * 리스트 검색 테스트 3: java8의 StreamAPI 사용
	 */
	@Test
	public void searchWithStream() {
		List<HashMap<String, Object>> list = getSomeList();
		List<HashMap<String, Object>> searchResult
				= list.stream().filter(ele -> "b".equals(ele.get("key")) || "d".equals(ele.get("key"))).collect(Collectors.toList());
		Assert.assertEquals(4, list.size()); // 원래 리스트는 변하지 않음
		Assert.assertEquals(2, searchResult.size()); // filter의 조건에 맞는 새로운 리스트의 길이
		Assert.assertEquals("456", searchResult.get(0).get("value"));
		Assert.assertEquals("012", searchResult.get(1).get("value"));
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
	public void fromArray() {
		Integer[] values = { 1, 3, 7 };
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(values));
		Assert.assertEquals("[1, 3, 7]", list.toString());
	}

	@Test
	public void fromArrayByStream() {
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

	/**
	 * sublist 테스트. substring처럼 인덱스 범위에 해당하는 요소를 추출한다.
	 * 
	 * @author fixalot
	 */
	@Test
	public void testSublist() {
		List<Integer> numbers = Arrays.asList(5, 3, 1, 2, 9, 5, 0, 7);

		List<Integer> head = numbers.subList(0, 4); // 5, 3, 1, 2
		Assert.assertEquals(4, head.size());
		Assert.assertEquals(Arrays.asList(5, 3, 1, 2), head);

		List<Integer> tail = numbers.subList(4, 8); // 9, 5, 0, 7
		Assert.assertEquals(4, tail.size());
		Assert.assertEquals(Arrays.asList(9, 5, 0, 7), tail);
	}

	@Test
	public void getString() {
		List<String> texts = Arrays.asList("a", "b", "c");
		Assert.assertEquals("a, b, c", String.join(", ", texts));

		List<Integer> numbers = Arrays.asList(1, 2, 3);
		Joiner joiner = Joiner.on(", ");
		Assert.assertEquals("1, 2, 3", joiner.join(numbers));
	}
}
