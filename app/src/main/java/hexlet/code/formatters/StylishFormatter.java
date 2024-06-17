package hexlet.code.formatters;

import hexlet.code.EntryStatus;
import hexlet.code.EntryFieldName;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StylishFormatter {
    private static final String SPACE = "  ";
    private static final String DELIMITER = ": ";
    private static final String ADDED_DELIMITER = "+ ";
    private static final String REMOVED_DELIMITER = "- ";

    public static String format(List<Map<String, Object>> entries) {
        String entriesString = entries.stream()
                .map(StylishFormatter::format)
                .collect(Collectors.joining("\n"));

        return "{\n" + entriesString + "\n}";
    }

    private static String format(Map<String, Object> entry) {
        String key = (String) entry.get(EntryFieldName.KEY);
        EntryStatus entryStatus = (EntryStatus) entry.get(EntryFieldName.STATUS);

        String value = String.valueOf(entry.get(EntryFieldName.VALUE));
        String valueOld = String.valueOf(entry.get(EntryFieldName.VALUE_OLD));
        String valueNew = String.valueOf(entry.get(EntryFieldName.VALUE_NEW));

        switch (entryStatus) {
            case NOT_CHANGED -> {
                return SPACE + SPACE + key + DELIMITER + value;
            }
            case CHANGED -> {
                return SPACE + REMOVED_DELIMITER + key + DELIMITER + valueOld + "\n"
                        + SPACE + ADDED_DELIMITER + key + DELIMITER + valueNew;
            }
            case ADDED -> {
                return SPACE + ADDED_DELIMITER + key + DELIMITER + valueNew;
            }
            case REMOVED -> {
                return SPACE + REMOVED_DELIMITER + key + DELIMITER + valueOld;
            }
            default -> throw new RuntimeException("Unsupported entry status!");
        }
    }
}
