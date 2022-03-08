package thirdparty.com.fasterxml.jackson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import lombok.ToString;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * com.fasterxml.jackson 테스트
 * 
 * @since 2019-01-23
 * @author fixalot
 */
public class JacksonTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(JacksonTest.class);

	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * 파싱할 문자열이 array[]인지 object{}인지 알 수 없을때 처리 방법 
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @author noritersand
	 */
	@Test
	public void testSingleValueAsArray() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		// 원래는 이런 문자만 파싱할 수 있지만...
//		String text = "{\"aaa\": \"Hello world!\", \"bbb\": [{\"ccc\": \"welcome to dead man club\"}]}";
		
		/*
		 * SINGLE_VALUE_AS_ARRAY 옵션을 설정하면 배열이 아니어도 파싱 가능
		 * 만약 이 설정이 없으면: MismatchedInputException: Cannot deserialize instance of
		 * `java.util.ArrayList<thirdparty.com.fasterxml.jackson.ElementObject>` out of START_OBJECT token 
		 */
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		String text = "{\"aaa\": \"Hello world!\", \"bbb\": {\"ccc\": \"welcome to dead man club\"}}";

		UpperObject upper = mapper.readValue(text, UpperObject.class);
		Assert.assertNotNull(upper);
		List<ElementObject> elementList = upper.getBbb();
		Assert.assertEquals(1, elementList.size());
		ElementObject element = elementList.get(0);
		Assert.assertEquals(ElementObject.class, element.getClass());
		Assert.assertEquals("welcome to dead man club", element.getCcc());
	}
	
	/**
	 * JSON 문자열 -> 인스턴스
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @author fixalot
	 */
	@Test
	public void testReadValue() throws JsonParseException, JsonMappingException, IOException {
		String jsonText = "[{\"html1\":\"<p>홀홀</p>\",\"html2\":\"<p>ㅗㅎ롷ㄹ</p>\"}]";
		final List<HashMap<String, Object>> collection
				= mapper.readValue(jsonText, new TypeReference<ArrayList<HashMap<String, Object>>>() {});
		Assert.assertEquals(1, collection.size());
		Assert.assertEquals("<p>홀홀</p>", collection.get(0).get("html1"));
		Assert.assertEquals("<p>ㅗㅎ롷ㄹ</p>", collection.get(0).get("html2"));
		Assert.assertEquals(ArrayList.class, collection.getClass());
		Assert.assertEquals(HashMap.class, collection.get(0).getClass());
	}
	
	@Test
	public void testReadValueWithAmbiguousTypeDeclare() throws JsonMappingException, JsonProcessingException {
		String jsonText = "[{\"html1\":\"<p>홀홀</p>\",\"html2\":\"<p>ㅗㅎ롷ㄹ</p>\"}]";
		final List<Object> collection
				= mapper.readValue(jsonText, new TypeReference<List<Object>>() {});
		Assert.assertEquals(1, collection.size());
		Assert.assertEquals(ArrayList.class, collection.getClass());
		Assert.assertEquals(LinkedHashMap.class, collection.get(0).getClass());
	}

	/**
	 * 인스턴스 -> JSON 문자열
	 * 
	 * @throws JsonProcessingException
	 * @author fixalot
	 */
	@Test
	public void testWriteValue() throws JsonProcessingException {
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "steave");
		map.put("age", "32");
		map.put("job", "baker");
		final String jsonText = mapper.writeValueAsString(map);
		Assert.assertEquals("{\"name\":\"steave\",\"job\":\"baker\",\"age\":\"32\"}", jsonText);
	}

	@Test
	public void testWriteValueWithCustomPOJO() throws JsonProcessingException {
		class Pojo {
			private String id;
			private String name;
			public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
		}

		Pojo e1 = new Pojo();
		e1.setId("123");
		e1.setName("abc");
		Assert.assertEquals("{\"id\":\"123\",\"name\":\"abc\"}", mapper.writeValueAsString(e1));

		List<Pojo> list = new ArrayList<>(1);
		list.add(e1);
		Assert.assertEquals("[{\"id\":\"123\",\"name\":\"abc\"}]", mapper.writeValueAsString(list));
	}

	/**
	 * 컬렉션 -> Plain Object로 변환
	 * 
	 * @author fixalot
	 */
	@Test
	public void testConvertValue() {
		// #1: map -> po
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", "steave");
		map.put("age", "32");
		map.put("dead", "true");
		
		PlainObject po = mapper.convertValue(map, PlainObject.class);
		Assert.assertEquals("steave", po.getName());
		Assert.assertEquals(Integer.valueOf(32), po.getAge());
		
		// #2: list<map> -> po
		HashMap<String, Object> map2 = new HashMap<>();
		map2.put("name", "soap");
		map2.put("age", 43);
		map2.put("dead", true);
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		list.add(map);
		list.add(map2);
		
		ArrayList<PlainObject> poList = mapper.convertValue(list, new TypeReference<ArrayList<PlainObject>>() {});
		Assert.assertEquals("soap", poList.get(1).getName());
		Assert.assertEquals(Integer.valueOf(32), poList.get(0).getAge());
		Assert.assertEquals(Integer.valueOf(43), poList.get(1).getAge());
		Assert.assertTrue(poList.get(0).isDead());
		Assert.assertTrue(poList.get(1).isDead());
	}
}
