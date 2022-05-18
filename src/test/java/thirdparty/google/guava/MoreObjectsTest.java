package thirdparty.google.guava;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class MoreObjectsTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MoreObjectsTest.class);

	@Test
	public void useToStringHelper() {
		ToStringHelper helper = MoreObjects.toStringHelper(PlainObject.class);
		logger.debug(helper.toString());

		PlainObject po = new PlainObject();
		po.setStringField("야");
		po.setIntField(65536);
		helper = MoreObjects.toStringHelper(po); // FIXME 이거 뭐하는 놈이여
		logger.debug(helper.toString());
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
