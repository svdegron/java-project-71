package hexlet.code;

import java.util.List;
import java.util.Map;

import static hexlet.code.formatters.Plain.toPlain;
import static hexlet.code.formatters.Stylish.toStylish;

public class Formatter {

    public static final String EQUAL = "equal";
    public static final String DELETE = "delete";
    public static final String ADD = "add";
    public static final String EDIT = "edit";

    public static final int ACTION = 0;
    public static final int FIRST_VALUE = 1;
    public static final int SECOND_VALUE = 2;

    public static String getResult(Map<String, List<String>> map, String format) {

        if ("plain".equals(format)) {
            return toPlain(map);
        }

        return toStylish(map);
    }

}
