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
}
