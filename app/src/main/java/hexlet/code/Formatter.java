package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Formatter {
    private static final Map<String, Function<List<Map<String, Object>>, String>> FORMAT_MAP = Map.of(
            "stylish", StylishFormatter::format,
            "plain", PlainFormatter::format,
            "json", JsonFormatter::format
    );

    public static String formatEntries(List<Map<String, Object>> entries, String format) {
        return FORMAT_MAP.get(format.toLowerCase()).apply(entries);
    }
}
