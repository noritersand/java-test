package constant;

public enum Fruit {
    APPLE(10), BANANA(9), COCONUT(12), DURIAN(15), EGGPLANT(8);
//		public static final Apple F = new Apple();

    private int price; // price of each apple

    // Constructor
    Fruit(int p) {
        price = p;
    }

    public int price() {
        return price;
    }

    public static Fruit valueOf(int price) {
        for (Fruit ele : Fruit.values()) {
            if (ele.price() == price) {
                return ele;
            }
        }
        return null;
    }
}
