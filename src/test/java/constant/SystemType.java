package constant;

public enum SystemType {
    BACK_OFFICE, FRONT_OFFICE("front office system");

    SystemType() {
    } // 기본 생성자

    private String optionalField;

    SystemType(String optionalField) {
        this.optionalField = optionalField;
    }

    public String optionalField() {
        return optionalField;
    }

    public static SystemType valueOfIAESafe(String arg) {
        try {
            return valueOf(arg);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
