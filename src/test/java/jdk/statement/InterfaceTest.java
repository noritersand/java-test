package jdk.statement;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 인터페이스 테스트 슈트
 * 
 * @since 2021-01-06
 * @author noritersand
 */
public class InterfaceTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(InterfaceTest.class);

	@Test
	public void shouldBeCompileError() {
//		ExtendMe extendMe = new ExtendMe(); // 추상 클래스는 인스턴스 생성 불가 Cannot instantiate the type ExtendMe
	}
	
	@Test
	public void test() {
		String fourfivesix = ImplementMe.fourFiveSix;
		Assert.assertEquals(fourfivesix, "456");
		ImplementMe aa = new ImplementMe() {
			
			@Override
			public String getText2() {
				
				return null;
			}
		};
		Assert.assertEquals("Hi.", aa.sayHello());
	}
}

interface ImplementMe {
	final String oneTwoThree = "123";
	final String fourFiveSix = "456";
	
	static String getAng() {
		return "ang?";
	}
	
	String getText2();
	
	default String sayHello() {
		return "Hi.";
	}
}

abstract class ExtendMe {
	public String getText() {
		return "Yo";
	}
	
	public abstract String getText2();
	
	abstract String getText3();
	
	protected abstract String getText4();
}

class ExtendYou extends ExtendMe {

	@Override
	public String getText2() {
		return null;
	}

	@Override
	String getText3() {
		return null;
	}

	@Override
	protected String getText4() {
		return null;
	}
	
}