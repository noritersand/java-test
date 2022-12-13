package lab.util.request;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
public enum RequestUtil {
    ;

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
        BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder builder = new StringBuilder();
        String buffer;
        while (null != (buffer = input.readLine())) {
            if (0 < builder.length()) {
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
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            if (null != inputStream) {

                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while (0 < (bytesRead = bufferedReader.read(charBuffer))) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
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
            log.debug("- {}: {}", headerName, request.getHeader(headerName));
        }
    }
}
