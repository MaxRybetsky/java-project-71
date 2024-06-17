package hexlet.code.formatters;

import hexlet.code.EntryStatus;
import hexlet.code.EntryFieldName;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlainFormatter {
    public static String format(List<Map<String, Object>> entries) {
        return entries.stream()
                .map(PlainFormatter::format)
                .filter(entryString -> !Objects.equals("", entryString))
                .collect(Collectors.joining("\n"));
    }

    private static String format(Map<String, Object> entry) {
        String key = (String) entry.get(EntryFieldName.KEY);
        EntryStatus entryStatus = (EntryStatus) entry.get(EntryFieldName.STATUS);

        String valueOld = toPlainString(entry.get(EntryFieldName.VALUE_OLD));
        String valueNew = toPlainString(entry.get(EntryFieldName.VALUE_NEW));

        switch (entryStatus) {
            case NOT_CHANGED -> {
                return "";
            }
            case CHANGED -> {
                return String.format("Property '%s' was updated. From %s to %s", key, valueOld, valueNew);
            }
            case ADDED -> {
                return String.format("Property '%s' was added with value: %s", key, valueNew);
            }
            case REMOVED -> {
                return String.format("Property '%s' was removed", key);
            }
            default -> throw new RuntimeException("Unsupported entry status!");
        }
    }

    private static String toPlainString(Object object) {
        if (object instanceof List || object instanceof Map<?, ?>) {
            return "[complex value]";
        } else if (object instanceof String) {
            return "'" + object + "'";
        } else {
            return String.valueOf(object);
        }
    }
}
