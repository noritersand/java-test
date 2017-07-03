package laboratory.thirdparty.spring;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

public class BeanUtilsTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BeanUtilsTest.class);

	/**
	 * 인스턴스의 프로퍼티 복사. private 내부 클래스에서 잘 작동함
	 * 
	 * @author fixalot
	 */
	@Test
	public void copyProperties() {
		TestModel oldbie = new TestModel();
		oldbie.setName("야");
		oldbie.setNumber(101);
		oldbie.setWhen(null);

		TestModel newbie = new TestModel();

		BeanUtils.copyProperties(oldbie, newbie);

		Assert.assertTrue(oldbie != newbie);
		Assert.assertEquals(oldbie.getName(), newbie.getName());
		Assert.assertEquals(oldbie.getNumber(), newbie.getNumber());
		Assert.assertEquals(oldbie.getWhen(), newbie.getWhen());
	}

	private class TestModel {
		private String name;
		private int number;
		private Date when;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public Date getWhen() {
			return when;
		}

		public void setWhen(Date when) {
			this.when = when;
		}
	}
}
