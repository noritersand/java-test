package thirdparty.apache.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link CollectionUtils} 테스트 유닛<br> 
 * 
 * @since 2020-03-13
 * @author noritersand
 */
public class CollectionUtilsTest {
	private static final Logger logger = LoggerFactory.getLogger(CollectionUtilsTest.class);
	
	/**
	 * {@link CollectionUtils#union(java.util.Collection, java.util.Collection)} 테스트<br>
	 * 
	 * @author noritersand
	 */
	@Test
	public void testUnionWithMap() {
		List<HashMap<String, Object>> a = generateMapList();
		List<HashMap<String, Object>> b = generateMapList();
		Assert.assertTrue(a != b);
		Assert.assertEquals(4, a.size());
		
		// b 리스트는 key=a만 남김 
		b = b.subList(0, 1);
		Assert.assertEquals(1, b.size());
		
		Assert.assertTrue(a.get(0) != b.get(0));
		
		// 합침
		@SuppressWarnings("unchecked")
		List<HashMap<String, Object>> c = (List<HashMap<String, Object>>) CollectionUtils.union(a, b);
		
		// 합치기 전 사이즈와 합친 후의 사이즈가 같음
		Assert.assertEquals(a.size(), c.size());
		// 아마 요소가 map이고 map의 값들이 모두 같으면 같은 요소라고 판단하는 모양임.
		logger.debug("{}", c);
	}
	
	/**
	 * Map 대신 POJO 사용해서 테스트
	 * 
	 * @author noritersand
	 */
	@Test
	public void testUnionWithPOJO1() {
		List<ListTestModel> x = generatePOJOList();
		List<ListTestModel> y = generatePOJOList();
		Assert.assertTrue(x != y);
		Assert.assertEquals(4, x.size());
		
		// y 리스트는 key=a만 남김 
		y = y.subList(0, 1);
		Assert.assertEquals(1, y.size());

		Assert.assertTrue(x.get(0) != y.get(0));
		
		// 합침
		@SuppressWarnings("unchecked")
		List<ListTestModel> c = (List<ListTestModel>) CollectionUtils.union(x, y);
		
		// 합치기 전 사이즈와 합친 후의 사이즈가 다름
		Assert.assertNotEquals(x.size(), c.size());
		
		// 결론: Map과 다르게 정말 같은 인스턴스인지 엄격히 비교함
		logger.debug("{}", c); // 그래서 보면 first가 두 개임
	}
	
	/**
	 * POJO 사용하되 서로 다른 리스트이지만 요소의 인스턴스가 같으면 어떻게 작동하는지 테스트
	 * 
	 * @author noritersand
	 */
	@Test
	public void testUnionWithPOJO2() {
		List<ListTestModel> x = generatePOJOList();
		List<ListTestModel> y = new ArrayList<>();
		Assert.assertTrue(x != y);
		Assert.assertEquals(4, x.size());
		
		// x에서 하나 꺼내 y에 추가
		y.add(x.get(0));
		Assert.assertEquals(1, y.size());
		
		// 합침
		@SuppressWarnings("unchecked")
		List<ListTestModel> c = (List<ListTestModel>) CollectionUtils.union(x, y);
		
		// 합치기 전 사이즈와 합친 후의 사이즈가 같음
		Assert.assertEquals(x.size(), c.size());
		
		// 결론: 같은 인스턴스니까 합치치 않고 무시함
		logger.debug("{}", c);
	}
	
	/**
	 * 리스트 검색 테스트
	 */
	@Test
	public void testSelect() {
		List<HashMap<String, Object>> list = generateMapList();
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
		List<HashMap<String, Object>> searchResult = (ArrayList<HashMap<String, Object>>) CollectionUtils.select(list, condition);
		Assert.assertEquals(4, list.size()); // 원래 리스트는 변하지 않음
		Assert.assertEquals(2, searchResult.size()); // filter의 조건에 맞는 새로운 리스트의 길이
		Assert.assertEquals("123", searchResult.get(0).get("value"));
		Assert.assertEquals("789", searchResult.get(1).get("value"));
	}
	
	@Test
	public void testReverseArray() {
//		int[] arr = new int[] { 1, 2, 6, 3, 4 };
		Integer[] arr = new Integer[] { 1, 2, 6, 3, 4 };
		CollectionUtils.reverseArray(arr);
		Assert.assertArrayEquals(new Integer[] { 4, 3, 6, 2, 1 }, arr);
	}
	
	private List<HashMap<String, Object>> generateMapList() {
		List<HashMap<String, Object>> list = new ArrayList<>();
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
	
	private List<ListTestModel> generatePOJOList() {
		List<ListTestModel> list = new ArrayList<ListTestModel>();
		ListTestModel model = new ListTestModel("first");
		list.add(model);
		model = new ListTestModel("second");
		list.add(model);
		model = new ListTestModel("third");
		list.add(model);
		model = new ListTestModel("fourth");
		list.add(model);
		return list;
	}
	
	private class ListTestModel {
		private String name;

		public ListTestModel(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "{name=" + name + "}";
		}
	}
}
