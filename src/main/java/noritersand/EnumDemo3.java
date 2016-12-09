package noritersand;

enum Apple {
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

public class EnumDemo3 {
	public static void main(String args[]) {
		// Display price of Winsap.
		System.out.println(Apple.A.getPrice());

		// Display all apples and prices.
		System.out.println("All apple prices:");
		for (Apple a : Apple.values()) {
			System.out.println(a + " costs " + a.getPrice() + " cents.");
		}
	}
}