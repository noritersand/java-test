package laboratory.test.apache.commons;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lang3Test {
//	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(Lang3Test.class);

	@Test
	public void testDefault() {
		Assert.assertEquals("", StringUtils.defaultString("", "0"));
		Assert.assertEquals("0", StringUtils.defaultIfBlank("", "0"));
	}

	@Test
	public void testPadding() {
		Assert.assertEquals("001", StringUtils.leftPad("1", 3, "0"));
		Assert.assertEquals("10000", StringUtils.rightPad("1", 5, "0"));
	}

	@Test
	public void testToStringBuilder() {
		Pojo pojo = new Pojo();
		log.debug(ToStringBuilder.reflectionToString(pojo));
		pojo.setStringField("야");
		pojo.setIntField(65536);
		log.debug(ToStringBuilder.reflectionToString(pojo));
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
