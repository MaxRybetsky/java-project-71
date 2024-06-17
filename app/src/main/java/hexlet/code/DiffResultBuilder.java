package hexlet.code;

import hexlet.code.formatters.StylishFormatter;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class DiffResultBuilder {
    private static final Map<String, Function<List<Map<EntryValueName, Object>>, String>> FORMAT_MAP = Map.of(
            "stylish", StylishFormatter::format
    );

    public static String formatEntries(List<Map<EntryValueName, Object>> entries, String format) {
        return FORMAT_MAP.get(format.toLowerCase()).apply(entries);
    }
}
