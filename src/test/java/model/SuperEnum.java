package model;

public interface SuperEnum {
    int ONE = 1;

    static String doSomething() {
        return "1234";
    }

//    static <T> Object of(? extends enum arg1, String arg) {
//        return null;
//    }

    String overrideMe();

//    public static PaymentCycle of(String arg) {
//        PaymentCycle[] values = PaymentCycle.values();
//        for (PaymentCycle ele : values) {
//            if (arg.equals(ele.getValue())) {
//                return ele;
//            }
//        }
//        return null;
//    }
}
