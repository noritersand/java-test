package lab.servlet.core.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * JSON 응답 객체. GSON으로 파싱해서 사용한다.
 *
 * @author fixalot
 * @since 2017-07-26
 */
public class JsonResponseObject {
    /**
     * 정상 처리 여부
     */
    private Boolean success;
    /**
     * 메시지
     */
    private String message;
    /**
     * 처리 상세 결과 코드
     */
    private String resultCode;
    /**
     * 개발자 정의 맵
     */
    private Map<String, Object> resultMap;

    public JsonResponseObject addResultMapItem(String key, Object value) {
        if (null == resultMap) {
            resultMap = new HashMap<>();
            resultMap.put(key, value);
        } else {
            resultMap.put(key, value);
        }
        return this;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public Map<String, Object> getResultMap() {
        return this.resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }
}
