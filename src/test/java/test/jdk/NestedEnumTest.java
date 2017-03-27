package test.jdk;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NestedEnumTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(NestedEnumTest.class);
	
	@Test
	public void test() {
		NestedEnumTestBean.InsertType insertType = NestedEnumTestBean.InsertType.APPEND;
		Assert.assertEquals("APPEND", insertType.toString());
	}
}

class NestedEnumTestBean {
	private InsertType emailInsertType;

	public enum InsertType {
		APPEND, NEW;
	}

	public InsertType getEmailInsertType() {
		return emailInsertType;
	}

	public void setEmailInsertType(InsertType emailInsertType) {
		this.emailInsertType = emailInsertType;
	}
}
