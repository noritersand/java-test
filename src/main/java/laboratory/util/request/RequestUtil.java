package laboratory.util.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestUtil {
	private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);

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

	/**
	 * request body를 읽어서 문자열 반환
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @author fixalot
	 */
	public static String readBody(HttpServletRequest request) {
		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			}
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					logger.error(ex.getMessage(), ex);
				}
			}
		}
		body = stringBuilder.toString();
		return body;
	}
}
