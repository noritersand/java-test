package constant;

public enum SystemType {
    BACK_OFFICE, FRONT_OFFICE("front office system");

    SystemType() {
    } // 기본 생성자

    private String label;

    SystemType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public static SystemType valueOfIAESafe(String arg) {
        try {
            return valueOf(arg);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
