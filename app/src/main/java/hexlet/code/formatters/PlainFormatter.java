package hexlet.code.formatters;

import hexlet.code.EntryStatus;
import hexlet.code.EntryValueName;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlainFormatter {
    public static String format(List<Map<EntryValueName, Object>> entries) {
        return entries.stream()
                .map(PlainFormatter::format)
                .filter(entryString -> !Objects.equals("", entryString))
                .collect(Collectors.joining("\n"));
    }

    private static String format(Map<EntryValueName, Object> entry) {
        String key = (String) entry.get(EntryValueName.KEY);
        EntryStatus entryStatus = (EntryStatus) entry.get(EntryValueName.STATUS);

        String value = toPlainString(entry.get(EntryValueName.VALUE));
        String valueOld = toPlainString(entry.get(EntryValueName.VALUE_OLD));
        String valueNew = toPlainString(entry.get(EntryValueName.VALUE_NEW));

        switch (entryStatus) {
            case NOT_CHANGED -> {
                return "";
            }
            case CHANGED -> {
                return String.format("Property '%s' was updated. From '%s' to '%s'", key, valueOld, valueNew);
            }
            case ADDED -> {
                return String.format("Property '%s' was added with value: '%s'", key, value);
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
        } else {
            return String.valueOf(object);
        }
    }
}
