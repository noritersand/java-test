package laboratory.jdk18.generic;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class GenericClassTest {
	@Test
	@SuppressWarnings("unused")
	public void shouldSuccess() {
		CustomGeneric<BigDecimal> gen = new CustomGeneric<>(); // correct
		CustomGeneric<LittleDecimal> gen2 = new CustomGeneric<>(); // correct
	}

	@Test
	public void shouldCompileError() {
//		CustomGeneric<Integer> gen3 = new CustomGeneric<>(); // wrong 
		// Bound mismatch: The type Integer is not a valid substitute for the bounded parameter <T extends BigDecimal> of the type
		// CustomGeneric<T>
	}

	private class CustomGeneric<T extends BigDecimal> {
		// T는 BigDecimal의 자식 클래스만 올 수 있음
	}

	private class LittleDecimal extends BigDecimal {
		private static final long serialVersionUID = 2718457985045593298L;

		public LittleDecimal(int val) {
			super(val);
		}
	}
}