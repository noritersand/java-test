package laboratory.thirdparty.apache.commons;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToStringBuilderTest {
//	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(ToStringBuilderTest.class);

	@Test
	public void testToStringBuilder() {
		Pojo pojo = new Pojo();
		log.debug(ToStringBuilder.reflectionToString(pojo));
		pojo.setStringField("야");
		pojo.setIntField(65536);
		log.debug(ToStringBuilder.reflectionToString(pojo));
		try {
			log.debug(ToStringBuilder.reflectionToString(null)); // 에러남
		} catch (Exception e) {
			// do nothing
		}
	}

	@SuppressWarnings("unused")
	private class Pojo {
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
