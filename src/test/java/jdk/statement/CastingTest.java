package jdk.statement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 캐스팅 테스트
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class CastingTest {
	private static final Logger logger = LoggerFactory.getLogger(CastingTest.class);

	@Test
	public void castNull() {
		Object imnotnotnull = null;
		String string = (String) imnotnotnull;
		assertNull(string);

		Integer integer = (Integer) imnotnotnull;
		assertNull(integer);
	}

	@Test
	public void upCasting() {
		Child c = new Child();
		Parent p = c;
		assertEquals("Child", p.getName());
//		p.play(); // undefined method
	}

	@Test
	public void downCasting() {
		// 요로케는 안됨.
		try {
			Parent p = new Parent();
			@SuppressWarnings("unused")
			Child c = (Child) p;
		} catch (ClassCastException e) {
			logger.debug("캐스팅 익셉션 발생함.");
		}
		
		// 요로케는 됨.
		Parent p = new Child();
		Child c = (Child) p;
		
		assertEquals("Child", c.getName());
		c.play();
	}
	
	private class Parent {
		public String getName() {
			return "Parent";
		}
	}
	
	private class Child extends Parent {
		public void play() {
			logger.debug("uoo-hoo!");
		}
		
		@Override
		public String getName() {
			return "Child";
		}
	}
}
