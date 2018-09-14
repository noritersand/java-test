package jdk.java.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpURLConnectionTest {
	private static final Logger logger = LoggerFactory.getLogger(HttpURLConnectionTest.class);
	
	@Test
	public void connectionTest() throws IOException {
		URL url = new URL("http://localhost:8080");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setDoOutput(true);
		con.setConnectTimeout(2000);
		con.setRequestMethod("GET");
		
		con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.9,ko;q=0.8");
//		con.setRequestProperty("Accept-Charset", "utf-8");
		con.setRequestProperty("Accept", "text/html");
//		con.setRequestProperty("Connection", "keep-alive");
//		con.setRequestProperty("Host", "localhost");
//		con.setRequestProperty("User-Agent", "java vm");
//		con.setRequestProperty("Cache-Control", "no-cache"); // 캐시 비활성화
//		con.setRequestProperty("Pragma", "no-cache"); // 캐시 비활성화
//		con.setRequestProperty("Content-Type", "text/html; charset=UTF-8");

		// 아래는 요청에 포함할 데이터가 있는 경우의 샘플
		/*
		con.setDoOutput(true);
		con.setConnectTimeout(2000);
		con.setRequestMethod(request.getMethod());
		if(request.getContentType() != null) {
			con.setRequestProperty("Content-Type", request.getContentType());
		}
		int clength = request.getContentLength();
		if(clength > 0) {
			con.setDoInput(true);
			InputStream istream = request.getInputStream();
			OutputStream os = con.getOutputStream();
			final int length = 5000;
			byte[] bytes = new byte[length];
			int bytesRead = 0;
			while ((bytesRead = istream.read(bytes, 0, length)) > 0) {
				os.write(bytes, 0, bytesRead);
			}
		}
		out.clear();
		out = pageContext.pushBody();
		*/
		
		logger.debug("response header: Content-Type: {}", con.getContentType());
		logger.debug("response code: {}", con.getResponseCode());
		
		InputStream in = con.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String singleline = "";
		while ((singleline = reader.readLine()) != null) {
			logger.debug(singleline);
		}

		reader.close();
		in.close();
		con.disconnect();
	}
}
