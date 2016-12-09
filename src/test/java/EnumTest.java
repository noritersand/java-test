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

enum Color {
	RED, BLUE, GREEN, YELLOW
}

public class EnumTest {
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
	}
}
