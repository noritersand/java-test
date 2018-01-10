package jdk.generic;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class GenericMethodTest {
	@Test
	public void shouldSuccess() {
		CustomGeneric gen = new CustomGeneric();
		gen.getSome(new LittleDecimal(123)); // correct
	}

	@Test
	public void shouldCompileError() {
//		gen.getSome("123"); // wrong
		// The method getSome(T) in the type GenericMethodTest.CustomGeneric is not applicable for the arguments (String)
//		gen.getSome(123); // wrong
		// The method getSome(T) in the type GenericMethodTest.CustomGeneric is not applicable for the arguments (int)
	}

	private class CustomGeneric {
		public <T extends BigDecimal> void getSome(T number) {
			System.out.println(number);
		}
	}

	private class LittleDecimal extends BigDecimal {
		private static final long serialVersionUID = 2718457985045593298L;

		public LittleDecimal(int val) {
			super(val);
		}
	}
}