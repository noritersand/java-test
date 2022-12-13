package constant;

public enum Fruit {
    APPLE(10), BANANA(9), COCONUT(12), DURIAN(15), EGGPLANT(8);
//		public static final Apple F = new Apple();

    private final int price; // price of each apple

    // Constructor
    Fruit(int p) {
        this.price = p;
    }

    public int price() {
        return this.price;
    }

    public static Fruit valueOf(int price) {
        for (Fruit ele : values()) {
            if (ele.price() == price) {
                return ele;
            }
        }
        return null;
    }
}
