package hexlet.code.formatters;

public enum PairState {
    EXIST("exist"),
    DELETE("delete"),
    ADD("add"),
    EDIT("edit");

    private final String label;

    PairState(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
