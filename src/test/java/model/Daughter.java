package model;

import lombok.Getter;
import lombok.Setter;

/**
 * 딸래미
 */
@Getter
@Setter
public class Daughter extends Mother implements Person {
    private String name;
    private int age;
    private String nation;

    public String print() {
        return "Child";
    }

    public static String staticPrint() {
        return "Child";
    }
}
