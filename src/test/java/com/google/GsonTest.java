package com.google;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class GsonTest {
	@Test
	public void testGson() {
		JsonObject job = new JsonObject();
		job.addProperty("key", "value");
		
		Assert.assertEquals("{\"key\":\"value\"}", new Gson().toJson(job));
	}
}
