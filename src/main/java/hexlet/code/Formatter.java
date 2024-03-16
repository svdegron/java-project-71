package hexlet.code;

import java.util.HashMap;
import java.util.Map;

public class Formatter {

    public static Map<String, Object> convertObjects(Map<String, Object> items) {
        var convertItems = new HashMap<String, Object>();

        for (String key : items.keySet()) {
            var entryValue = items.get(key);

            if (entryValue != null) {
                String cls = entryValue.getClass().toString();

                if (cls.indexOf("ArrayList") > 0 || cls.indexOf("LinkedHashMap") > 0) {
                    var str = entryValue.toString();
                    convertItems.put(key, str);
                } else {
                    convertItems.put(key, entryValue);
                }
            } else {
                convertItems.put(key, "null");
            }
        }

        return convertItems;
    }
}
