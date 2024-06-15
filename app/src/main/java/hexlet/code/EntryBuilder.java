package hexlet.code;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static hexlet.code.EntryStatus.ADDED;
import static hexlet.code.EntryStatus.CHANGED;
import static hexlet.code.EntryStatus.NOT_CHANGED;
import static hexlet.code.EntryStatus.REMOVED;

public class EntryBuilder {
    public static List<Map<EntryValueName, Object>> build(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keys1 = map1.keySet();
        Set<String> keys2 = map2.keySet();

        Set<String> allKeys = new HashSet<>(keys1);
        allKeys.addAll(keys2);

        return allKeys.stream()
                .map(key -> map(key, map1, map2))
                .toList();
    }

    private static Map<EntryValueName, Object> map(String key, Map<String, Object> map1, Map<String, Object> map2) {
        Map<EntryValueName, Object> result = new EnumMap<>(EntryValueName.class);

        Object valueOld = map1.get(key);
        Object valueNew = map2.get(key);

        result.put(EntryValueName.KEY, key);

        if (!map1.containsKey(key) && map2.containsKey(key)) {
            result.put(EntryValueName.VALUE_NEW, valueNew);
            result.put(EntryValueName.STATUS, ADDED);
        } else if (map1.containsKey(key) && !map2.containsKey(key)) {
            result.put(EntryValueName.VALUE_OLD, valueOld);
            result.put(EntryValueName.STATUS, REMOVED);
        } else if (map1.containsKey(key) && map2.containsKey(key) && Objects.equals(valueOld, valueNew)) {
            result.put(EntryValueName.VALUE, valueOld);
            result.put(EntryValueName.STATUS, NOT_CHANGED);
        } else if (map1.containsKey(key) && map2.containsKey(key) && !Objects.equals(valueOld, valueNew)) {
            result.put(EntryValueName.VALUE_OLD, valueOld);
            result.put(EntryValueName.VALUE_NEW, valueNew);
            result.put(EntryValueName.STATUS, CHANGED);
        }

        return result;
    }
}
