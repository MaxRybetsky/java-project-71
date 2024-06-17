package hexlet.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import static hexlet.code.EntryStatus.ADDED;
import static hexlet.code.EntryStatus.CHANGED;
import static hexlet.code.EntryStatus.NOT_CHANGED;
import static hexlet.code.EntryStatus.REMOVED;

public class EntryBuilder {
    public static List<Map<String, Object>> build(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keys1 = map1.keySet();
        Set<String> keys2 = map2.keySet();

        Set<String> allKeys = new TreeSet<>(keys1);
        allKeys.addAll(keys2);

        return allKeys.stream()
                .map(key -> map(key, map1, map2))
                .toList();
    }

    private static Map<String, Object> map(String key, Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Object> result = new HashMap<>();

        Object valueOld = map1.get(key);
        Object valueNew = map2.get(key);

        result.put(EntryFieldName.KEY, key);

        if (!map1.containsKey(key) && map2.containsKey(key)) {
            result.put(EntryFieldName.VALUE_NEW, valueNew);
            result.put(EntryFieldName.STATUS, ADDED);
        } else if (map1.containsKey(key) && !map2.containsKey(key)) {
            result.put(EntryFieldName.VALUE_OLD, valueOld);
            result.put(EntryFieldName.STATUS, REMOVED);
        } else if (map1.containsKey(key) && map2.containsKey(key) && Objects.equals(valueOld, valueNew)) {
            result.put(EntryFieldName.VALUE, valueOld);
            result.put(EntryFieldName.STATUS, NOT_CHANGED);
        } else if (map1.containsKey(key) && map2.containsKey(key) && !Objects.equals(valueOld, valueNew)) {
            result.put(EntryFieldName.VALUE_OLD, valueOld);
            result.put(EntryFieldName.VALUE_NEW, valueNew);
            result.put(EntryFieldName.STATUS, CHANGED);
        }

        return result;
    }
}
