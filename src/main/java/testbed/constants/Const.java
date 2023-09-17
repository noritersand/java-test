package testbed.constants;

public enum Const {
    NAME("waldo");

    private final String label;

    Const(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
