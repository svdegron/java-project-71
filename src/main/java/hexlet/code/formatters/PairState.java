package hexlet.code.formatters;

public enum PairState {
    EXIST("exist"),
    DELETE("delete"),
    ADD("add"),
    EDIT("edit");

    public final String label;

    private PairState(String label) {
        this.label = label;
    }
}
