package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DiffResultBuilder {
    private static final String SPACE = "  ";
    private static final String DELIMITER = ": ";
    private static final String ADDED_DELIMITER = "+ ";
    private static final String REMOVED_DELIMITER = "- ";

    public static String formatEntries(List<Map<EntryValueName, Object>> entries) {
        String entriesString = entries.stream()
                .map(DiffResultBuilder::format)
                .collect(Collectors.joining("\n"));

        return "{\n" + entriesString + "\n}";
    }

    private static String format(Map<EntryValueName, Object> entry) {
        String key = (String) entry.get(EntryValueName.KEY);
        EntryStatus entryStatus = (EntryStatus) entry.get(EntryValueName.STATUS);

        String value = String.valueOf(entry.get(EntryValueName.VALUE));
        String valueOld = String.valueOf(entry.get(EntryValueName.VALUE_OLD));
        String valueNew = String.valueOf(entry.get(EntryValueName.VALUE_NEW));

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
