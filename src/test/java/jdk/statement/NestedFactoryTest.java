package jdk.statement;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jdk.statement.NestedClassFactory.NestedClass;

/**
 * non-static 중첩 클래스 공장장 테스트
 * 
 * @since 2019-02-25
 * @author fixalot
 */
public class NestedFactoryTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(NestedFactoryTest.class);

	@Test
	public void test() {
		// #1 내부 클래스가 public일 때: 인스턴스 직접 접근
		NestedClass inner = NestedClassFactory.getNestedClass();
		Assert.assertNotNull(inner);

		// #2 내부 클래스가 private일 때: 인스턴스 직접 접근 불가.
		// compile error: The type NestedClassFactory.NestedHiddenClass is not visible
		// NestedClassFactory.NestedHiddenClass inner2 = NestedClassFactory.getNestedHiddenClass();
		Assert.assertEquals("fire egg", NestedClassFactory.getValueOfNestedHiddenClass());
	}
}

class NestedClassFactory {
	/**
	 * 인스턴스 생성 방지용 생성자 선언. 중첩 클래스와는 관계 없음.
	 */
	private NestedClassFactory() {}

	/**
	 * 중첩 클래스 #1
	 */
	public class NestedClass {}

	/**
	 * 중첩 클래스 #2 
	 */
	private class NestedHiddenClass {
		public String getValue() {
			return "fire egg";
		}
	}

	public static NestedClass getNestedClass() {
		NestedClass inner = new NestedClassFactory().new NestedClass();
		return inner;
	}

	public static NestedHiddenClass getNestedHiddenClass() {
		NestedHiddenClass inner = new NestedClassFactory().new NestedHiddenClass();
		return inner;
	}

	public static String getValueOfNestedHiddenClass() {
		NestedHiddenClass inner = new NestedClassFactory().new NestedHiddenClass();
		return inner.getValue();
	}
}