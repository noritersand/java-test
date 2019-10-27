package thirdparty.com.fasterxml.jackson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * com.fasterxml.jackson 테스트 유닛
 * 
 * @since 2019-01-23
 * @author fixalot
 */
public class JacksonTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(JacksonTest.class);

	private ObjectMapper mapper = new ObjectMapper();

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
		Assert.assertEquals(true, poList.get(0).isDead());
		Assert.assertEquals(true, poList.get(1).isDead());
	}
}
