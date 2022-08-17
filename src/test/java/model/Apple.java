package model;

public enum Apple {
    A(10), B(9), C(12), D(15), E(8);
//		public static final Apple F = new Apple();

    private int price; // price of each apple

    // Constructor
    private Apple(int p) {
        price = p;
    }

    public int getPrice() {
        return price;
    }

    public static Apple valueOf(int price) {
        for (Apple ele : Apple.values()) {
            if (ele.getPrice() == price) {
                return ele;
            }
        }
        return null;
    }
}
