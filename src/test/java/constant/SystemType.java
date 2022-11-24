package constant;

public enum SystemType {
    BACK_OFFICE, FRONT_OFFICE("front office system");

    private SystemType() {
    } // 기본 생성자

    private String optionalField;

    private SystemType(String optionalField) {
        this.optionalField = optionalField;
    }

    public String optionalField() {
        return this.optionalField;
    }

    public static SystemType valueOfIAESafe(String arg) {
        try {
            return SystemType.valueOf(arg);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
