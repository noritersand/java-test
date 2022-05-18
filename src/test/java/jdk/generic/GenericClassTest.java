package jdk.generic;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 제네릭 클래스 테스트.
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class GenericClassTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(GenericClassTest.class);

	@Test
	public void testNormalGeneric() {
		NormalGeneric<String> gen = new NormalGeneric<>();
		gen.setName("Waldo");
		assertEquals("Waldo", gen.getName());
	}

	@Test
	@SuppressWarnings("unused")
	public void testRestrictedGeneric() {
		RestrictedGeneric<BigDecimal> gen = new RestrictedGeneric<>(); // correct
		RestrictedGeneric<LittleDecimal> gen2 = new RestrictedGeneric<>(); // correct
	}

	@Test
	public void shouldCompileError() {
//		CustomGeneric<Integer> gen3 = new CustomGeneric<>(); // wrong 
		// Bound mismatch: The type Integer is not a valid substitute for the bounded parameter <T extends BigDecimal> of the type
		// CustomGeneric<T>
	}

	@Test
	public void testGenericMethod() {
		GenericMethodOnly gen = new GenericMethodOnly();
		assertTrue(Integer.class == gen.getType(Integer.valueOf(123)));
		assertTrue(String.class == gen.getType("Text"));
	}
	
	@Test
	public void testLimitedGeneric() {
//		LimitedGeneric<Integer> a = new LimitedGeneric<>(); // Bound mismatch: The type Integer is not a valid substitute for the bounded parameter <T extends BigDecimal> of the type LimitedGeneric<T>
	}

	/**
	 * 평범한 제네릭 클래스
	 * 
	 * @param <T>
	 * @since 2019-12-09
	 * @author noritersand
	 */
	private class NormalGeneric<T> {
		private T name;

		public T getName() {
			return name;
		}

		public void setName(T name) {
			this.name = name;
		}
	}

	/**
	 * 선언 가능한 타입이 제한된 제네릭 클래스. 이 경우 BigDecimal을 상속받은 타입만 제네릭 타입으로 선언할 수 있다.
	 * 
	 * @param <T>
	 * @since 2019-12-09
	 * @author noritersand
	 */
	private class RestrictedGeneric<T extends BigDecimal> {
		// T는 BigDecimal의 자식 클래스만 올 수 있음
	}

	private class LittleDecimal extends BigDecimal {

		private static final long serialVersionUID = 2718457985045593298L;

		public LittleDecimal(int val) {
			super(val);
		}
	}

	/**
	 * 제네릭 메서드만 있는 클래스.
	 * 
	 * @since 2019-12-09
	 * @author noritersand
	 */
	private class GenericMethodOnly {

		public <T> Class<?> getType(T arg) { // 리턴타입은 String임. <T>가 아니고
//	        return arg.getClass().toString();
			return arg.getClass();
		}
	}
}

class LimitedGeneric<T extends BigDecimal> {
	@SuppressWarnings("unused")
	private T value1;
}
