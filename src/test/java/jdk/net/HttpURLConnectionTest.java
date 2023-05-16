package jdk.net;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
public class HttpURLConnectionTest {

    /**
     * 로컬 서버 안띄워놓으면 접속 안되서 막음.
     *
     * @throws IOException
     * @author fixal
     */
//	@Test
    void connectionTest() throws IOException {
        URL url = new URL("http://localhost:8080");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setConnectTimeout(2000);
        con.setRequestMethod("GET");

        con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.9,ko;q=0.8");
        con.setRequestProperty("Accept", "text/html");
//		con.setRequestProperty("Accept-Charset", "utf-8");
//		con.setRequestProperty("Connection", "keep-alive");
//		con.setRequestProperty("Host", "localhost");
//		con.setRequestProperty("User-Agent", "java vm");
//		con.setRequestProperty("Cache-Control", "no-cache"); // 캐시 비활성화
//		con.setRequestProperty("Pragma", "no-cache"); // 캐시 비활성화
//		con.setRequestProperty("Content-Type", "text/html; charset=UTF-8");

        log.debug("response header: Content-Type: {}", con.getContentType());
        log.debug("response code: {}", con.getResponseCode());

        InputStream in = con.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        String singleline = "";
        while (null != (singleline = reader.readLine())) {
            log.debug(singleline);
        }

        reader.close();
        in.close();
        con.disconnect();
    }

    /**
     * 원래 JSP에 있는 소스 퍼옴.
     *
     * @author fixalot
     */
    public void example1() {
		/*
		String[] serverUrls = { "https://some-media-server.com" };
		
		String reqUrl = request.getQueryString();
		if(reqUrl.indexOf("&_=") > -1){
			reqUrl = reqUrl.substring(0, reqUrl.indexOf("&_="));
		}
		boolean allowed = false;
		String token = null;
		for(String surl : serverUrls) {
			String[] stokens = surl.split("\\s*,\\s*");
		    if(reqUrl.toLowerCase().contains(stokens[0].toLowerCase())) {
		    	allowed = true;
		      	if(stokens.length >= 2 && stokens[1].length() > 0)
		        	token = stokens[1];
		      	break;
		    }
		}
		if(!allowed) {
			response.setStatus(403);
		    return;
		}
		if(token != null) {
		    reqUrl = reqUrl + (reqUrl.indexOf("?") > -1 ? "&" : "?") + "token=" + token;
		}
	
		URL url = new URL(reqUrl);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
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
	
		OutputStream ostream = response.getOutputStream();
		response.setContentType(con.getContentType());
		InputStream in = con.getInputStream();
		final int length = 5000;
		byte[] bytes = new byte[length];
		int bytesRead = 0;
		while ((bytesRead = in.read(bytes, 0, length)) > 0) {
			ostream.write(bytes, 0, bytesRead);
		}
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		*/
    }
}
