package model;

import java.util.Date;

/**
 * 테스트용 모델
 *
 * @author fixalot
 * @since 2017-06-26
 */
public class TestModel {
    private String name;
    private int number;
    private Date when;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getWhen() {
        return this.when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }
}
