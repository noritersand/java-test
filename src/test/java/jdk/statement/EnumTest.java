package jdk.statement;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class EnumTest {
	private static final Logger logger = LoggerFactory.getLogger(EnumTest.class);

	public static void main(String[] args) {
		System.out.println(Color.RED); // RED
		System.out.println(Color.RED.toString()); // RED
		System.out.println(Color.RED.name()); // RED

		// Display price of Winsap.
		System.out.println(Apple.A.getPrice());
		// Display all apples and prices.
		System.out.println("All apple prices:");
		for (Apple ele : Apple.values()) {
			System.out.println(ele + " costs " + ele.getPrice() + " cents.");
		}

		SystemType var1 = SystemType.BACK_OFFICE;
		SystemType var2 = SystemType.FRONT_OFFICE;

		System.out.println(var1); // BACK_OFFICE
		System.out.println(var2); // FRONT_OFFICE
		System.out.println(var2.name()); // FRONT_OFFICE
		System.out.println(var2.getOptionalField()); // front office system
	}

	@Test
	public void useSwitch() {
		String div = "back_office";
		switch (SystemType.valueOf(div.toUpperCase())) {
		case BACK_OFFICE:
			logger.debug("백");
			break;
		case FRONT_OFFICE:
			logger.debug("프론트");
			break;
		}
	}

	@Test
	public void fromString() {
		Class<Color> clazz = Color.class;
		String source = "red";
		Enum<Color> color = Enum.valueOf(clazz, source.trim().toUpperCase());
		Assert.assertEquals(Color.RED, color);
	}
	
	@Test
	public void testValueOfIAESafe() {
		String txt = "NOT_BACK_OFFICE";
		SystemType systemType = SystemType.valueOfIAESafe(txt);
		Assert.assertEquals("BACK_OFFICE", String.valueOf(systemType));
	}
	
	@Test
	public void testGetDeclaringClass() {
		Assert.assertEquals(SystemType.BACK_OFFICE.getClass(), SystemType.BACK_OFFICE.getDeclaringClass());
		Assert.assertEquals(SystemType.FRONT_OFFICE.getDeclaringClass(), SystemType.BACK_OFFICE.getDeclaringClass());
		Assert.assertNotEquals(Color.RED.getDeclaringClass(), SystemType.FRONT_OFFICE.getDeclaringClass());
	}

	private enum SystemType {
		BACK_OFFICE, FRONT_OFFICE("front office system");

		private SystemType() {} // 기본 생성자

		private String optionalField;

		private SystemType(final String option) {
			this.optionalField = option;
		}

		public String getOptionalField() {
			return this.optionalField;
		}
		
		public static SystemType valueOfIAESafe(String arg) {
			try {
				return SystemType.valueOf(arg);
			} catch (IllegalArgumentException e) {
				logger.debug(e.getMessage());
				return BACK_OFFICE;
			}
		}
	}

	private enum Apple {
		A(10), B(9), C(12), D(15), E(8);

		private int price; // price of each apple

		// Constructor
		private Apple(int p) {
			price = p;
		}

		public int getPrice() {
			return price;
		}
	}

	private enum Color {
		RED, BLUE, GREEN, YELLOW
	}
}