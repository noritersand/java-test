package thirdparty.apache.commons;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class ToStringBuilderTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ToStringBuilderTest.class);

	@Test
	public void printToStringResult() {
		PlainObject po = new PlainObject();
		logger.debug(ToStringBuilder.reflectionToString(po));
		po.setStringField("야");
		po.setIntField(65536);
		logger.debug(ToStringBuilder.reflectionToString(po));
		try {
			logger.debug(ToStringBuilder.reflectionToString(null)); // 에러남
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
