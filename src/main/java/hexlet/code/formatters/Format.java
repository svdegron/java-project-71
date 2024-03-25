package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;

import static hexlet.code.formatters.Json.toJson;
import static hexlet.code.formatters.Plain.toPlain;

public enum Format {
    STYLISH("stylish"),
    PLAIN("plain"),
    JSON("json");

    public final String label;

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
