package hexlet.code.formatters;

public enum Format {
    STYLISH("stylish"),
    PLAIN("plain"),
    JSON("json");

    private final String label;

    Format(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public static Format valueOfLabel(String label) {
        for (Format e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
