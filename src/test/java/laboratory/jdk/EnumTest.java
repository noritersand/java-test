package laboratory.jdk;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnumTest {
	private static final Logger log = LoggerFactory.getLogger(EnumTest.class);
	
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

		SystemDivision var1 = SystemDivision.BACK_OFFICE;
		SystemDivision var2 = SystemDivision.FRONT_OFFICE;

		System.out.println(var1); // BACK_OFFICE
		System.out.println(var2); // FRONT_OFFICE
		System.out.println(var2.name()); // FRONT_OFFICE
		System.out.println(var2.getOptionalField()); // front office system
	}
	
	@Test
	public void useSwitch() {
		String div = "back_office";
		switch (SystemDivision.valueOf(div.toUpperCase())) {
		case BACK_OFFICE:
			log.debug("백");
			break;
		case FRONT_OFFICE:
			log.debug("프론트");
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
	
	private enum SystemDivision {
		BACK_OFFICE, FRONT_OFFICE("front office system");

		private SystemDivision() {
		} // 기본 생성자

		private String optionalField;

		private SystemDivision(final String option) {
			this.optionalField = option;
		}

		public String getOptionalField() {
			return this.optionalField;
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