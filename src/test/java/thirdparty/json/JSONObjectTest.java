package thirdparty.json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONObjectTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(JSONObjectTest.class);
	
	@Test
	public void test() {
		// 단일 객체
		JSONObject ob = new JSONObject();
		ob.put("이름1", "값1");
		ob.put("이름2", "값2");
		
		Assert.assertEquals("{\"이름2\":\"값2\",\"이름1\":\"값1\"}", ob.toString());

		// 객체 배열
		JSONArray list = new JSONArray();

		JSONObject ob2 = new JSONObject();
		ob2.put("이름1", "값1");
		ob2.put("이름2", "값2");
		list.put(ob2);

		ob2 = new JSONObject();
		ob2.put("이름1", "값1");
		ob2.put("이름2", "값2");
		list.put(ob2);
		
		Assert.assertEquals("[{\"이름2\":\"값2\",\"이름1\":\"값1\"},{\"이름2\":\"값2\",\"이름1\":\"값1\"}]", list.toString());
	}
	
	@Test
	public void parseFromString() {
		final String jsonString = "{\"status\":\"0000\",\"data\":[[1582464360000,\"321000\",\"320900\",\"321000\",\"320900\",\"11.7071\"],[],[]]}";
		JSONObject object = new JSONObject(jsonString);

		JSONArray data = (JSONArray) object.get("data");
		Assert.assertEquals(JSONArray.class, data.get(0).getClass());

		JSONArray firstElement = (JSONArray) data.get(0);
		Assert.assertEquals(Long.class, firstElement.get(0).getClass());
		Assert.assertEquals(String.class, firstElement.get(1).getClass());

		JSONArray secondEmptyElement = (JSONArray) data.get(1);
		Assert.assertEquals(0, secondEmptyElement.length());
	}
}
