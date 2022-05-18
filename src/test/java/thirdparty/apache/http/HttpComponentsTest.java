package thirdparty.apache.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class HttpComponentsTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(HttpComponentsTest.class);

	@Test
	public void send() throws Exception {
		String result = sendSimpleRequest("http://google.com");
		assertTrue(result != null && !result.isEmpty());
		logger.debug(result);
	}

	/**
	 * 간단한 요청 전송. 응답의 문자열을 String으로 만들때 UTF-8 케릭터셋을 적용하도록 되어 있음.
	 * 
	 * @param uri
	 * @return
	 * @throws Exception
	 * @author fixalot
	 */
	private String sendSimpleRequest(String uri) throws Exception {
		HttpGet request = new HttpGet(uri);
		try (CloseableHttpClient httpclient = HttpClients.createDefault(); CloseableHttpResponse response = httpclient.execute(request);) {

//			logger.debug("{}", Arrays.toString(response.getAllHeaders()));
//			logger.debug("{}", response.getStatusLine().getStatusCode());
//			logger.debug("{}", response.getStatusLine().getProtocolVersion());
//			logger.debug("{}", response.getStatusLine().getReasonPhrase());

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

	/**
	 * TODO 메서드 테스트해봐야함.
	 * 
	 * @param url
	 * @param param
	 * @return
	 * @throws IOException
	 * @author fixalot
	 */
	@SuppressWarnings("unused")
	private String sendPostRequest(String url, String param) throws IOException {
		URL object = new URL(url);

		HttpURLConnection con = (HttpURLConnection) object.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Accept", "application/x-www-form-urlencoded");
		con.setRequestMethod("POST");

		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

		wr.write(param);
		wr.flush();

		// display what returns the POST request

		StringBuilder sb = new StringBuilder();
		int HttpResult = con.getResponseCode();
		if (HttpResult == HttpURLConnection.HTTP_OK) {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			String line = null;

			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();

			System.out.println("" + sb.toString());

		} else {
			System.out.println(con.getResponseMessage());
		}

		return sb.toString();
	}
}
