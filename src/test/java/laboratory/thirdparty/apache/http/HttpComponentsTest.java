package laboratory.thirdparty.apache.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpComponentsTest {
//	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(HttpComponentsTest.class);

	@Test
	public void testGetRequest() throws Exception {
		String result = sendSimpleRequest("http://google.com");
		Assert.assertTrue(result != null && !result.isEmpty());
		log.debug(result);
	}

	private String sendSimpleRequest(String uri) throws Exception {
		HttpGet request = new HttpGet(uri);
		try (CloseableHttpClient httpclient = HttpClients.createDefault();
				CloseableHttpResponse response = httpclient.execute(request);) {
			
//			log.debug(String.valueOf(Arrays.toString(response.getAllHeaders())));
//			log.debug(String.valueOf(response.getStatusLine().getStatusCode()));
//			log.debug(String.valueOf(response.getStatusLine().getProtocolVersion()));
//			log.debug(String.valueOf(response.getStatusLine().getReasonPhrase()));
			
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String result = EntityUtils.toString(entity, "UTF-8");
				return result;
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}
}
