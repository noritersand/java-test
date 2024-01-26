package thirdparty.google.guava;

import com.google.common.base.MoreObjects;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class MoreObjectsTest {

    @Test
    void useToStringHelper() {
        MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(PlainObject.class);
        log.debug(helper.toString());

        PlainObject po = new PlainObject();
        po.setStringField("야");
        po.setIntField(65536);
        helper = MoreObjects.toStringHelper(po); // FIXME 이거 뭐하는 놈이여
        log.debug(helper.toString());
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
