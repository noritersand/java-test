package model;

import lombok.Getter;
import lombok.Setter;

/**
 * 엄니
 */
@Getter
@Setter
public class Mother implements Person {
    private String name;
    private int age;
    private String nation;

    public String print() {
        return "Parent";
    }

    public static String staticPrint() {
        return "Parent";
    }
}
