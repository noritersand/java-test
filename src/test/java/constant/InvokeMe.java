package constant;

public enum InvokeMe {
    TEN("10");

    private final String label;

    InvokeMe(String label) {
        this.label = label;
    }

    /**
     * Returns the name of this enum constant, as contained in the
     * declaration.  This method may be overridden, though it typically
     * isn't necessary or desirable.  An enum class should override this
     * method when a more "programmer-friendly" string form exists.
     *
     * @return the name of this enum constant
     */
    @Override
    public String toString() {
        return label;
    }
}
