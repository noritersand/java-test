package thirdparty.apache.commons;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.jupiter.api.Test;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class ToStringBuilderTest {

    @Test
    public void printToStringResult() {
        PlainObject po = new PlainObject();
        log.debug(ToStringBuilder.reflectionToString(po));
        po.setStringField("야");
        po.setIntField(65536);
        log.debug(ToStringBuilder.reflectionToString(po));
        try {
            log.debug(ToStringBuilder.reflectionToString(null)); // 에러남
        } catch (Exception e) {
            // do nothing
        }
    }

    @SuppressWarnings("unused")
    private class PlainObject {
        private String stringField;
        private Integer intField;

        public String getStringField() {
            return stringField;
        }

        public void setStringField(String stringField) {
            this.stringField = stringField;
        }

        public Integer getIntField() {
            return intField;
        }

        public void setIntField(Integer intField) {
            this.intField = intField;
        }
    }
}
