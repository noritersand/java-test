package laboratory.util.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestUtil {
	private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);

	private RequestUtil() {}

	/**
	 * 모든 파라미터의 문자열 반환
	 * 
	 * @param request
	 * @return
	 * @author fixalot
	 */
	public static RequestParameter getRequestParameter(HttpServletRequest request) {
		RequestParameter rp = new RequestParameter();

		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<String> keySet = parameterMap.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			String[] values = parameterMap.get(key);
			rp.addParameter(key, Arrays.toString(values));
		}

		return rp;
	}

	public static String readBody(HttpServletRequest request) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String buffer;
		while ((buffer = input.readLine()) != null) {
			if (builder.length() > 0) {
				builder.append("\n");
			}
			builder.append(buffer);
		}
		return builder.toString();
	}

	/**
	 * request body를 읽어서 문자열 반환
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @author fixalot
	 * @deprecated 옛날 방식이니 사용 금지
	 */
	@Deprecated(since = "??", forRemoval = true)
	public static String readBodyLegacyway(HttpServletRequest request) {
		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		try (InputStream inputStream = request.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));) {
			if (inputStream != null) {

				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			}
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		}
		body = stringBuilder.toString();
		return body;
	}

	/**
	 * 헤더 모두 출력
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getHeadersMap(HttpServletRequest request) {
		Enumeration<String> headerNames = request.getHeaderNames();
		HashMap<String, String> headerMap = new HashMap<>();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			headerMap.put(headerName, request.getHeader(headerName));
		}
		if (headerMap.isEmpty()) {
			return null;
		}
		return headerMap;
	}

	/**
	 * 헤더 모두 출력
	 * 
	 * @param request
	 */
	public static void printHeaders(HttpServletRequest request) {
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			logger.debug("- {}: {}", headerName, request.getHeader(headerName));
		}
	}
}
