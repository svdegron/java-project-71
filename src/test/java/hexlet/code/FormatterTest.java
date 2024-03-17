package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormatterTest {

//    @Test
//    public void convertObjectsSuccess() {
//        var list1 = new ArrayList<>(List.of(5, 6, 7, 8));
//        var list2 = new ArrayList<>(List.of("a1", "B2", "Cc3", "d45"));
//        var list3 = new ArrayList<>(List.of(true, false, false, true));
//        var list4 = new ArrayList<>(List.of(9, 10, 11, 12));
//
//        var map1 = new LinkedHashMap<String, Object>();
//        map1.put("value1", true);
//        map1.put("value2", 6543);
//        map1.put("value3", "ABCdef");
//        map1.put("value4", list4);
//
//        var items = new HashMap<String, Object>();
//        items.put("item1", true);
//        items.put("item2", 1234);
//        items.put("item3", "abcdEFGH");
//        items.put("item4", list1);
//        items.put("item5", list2);
//        items.put("item6", list3);
//        items.put("item7", map1);
//        items.put("item8", null);
//
//        var expectedItems = new HashMap<String, Object>();
//        expectedItems.put("item1", true);
//        expectedItems.put("item2", 1234);
//        expectedItems.put("item3", "abcdEFGH");
//        expectedItems.put("item4", "[5, 6, 7, 8]");
//        expectedItems.put("item5", "[a1, B2, Cc3, d45]");
//        expectedItems.put("item6", "[true, false, false, true]");
//        expectedItems.put("item7", "{value1=true, value2=6543, value3=ABCdef, value4=[9, 10, 11, 12]}");
//        expectedItems.put("item8", "null");
//
//        assertEquals(expectedItems, convertObjects(items));
//    }
}
