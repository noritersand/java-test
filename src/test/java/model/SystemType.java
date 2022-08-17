package model;

public enum SystemType {
    BACK_OFFICE, FRONT_OFFICE("front office system");

    private SystemType() {
    } // 기본 생성자

    private String optionalField;

    private SystemType(final String option) {
        this.optionalField = option;
    }

    public String getOptionalField() {
        return this.optionalField;
    }

    public static SystemType valueOfIAESafe(String arg) {
        try {
            return SystemType.valueOf(arg);
        } catch (IllegalArgumentException e) {
            return BACK_OFFICE;
        }
    }
}
