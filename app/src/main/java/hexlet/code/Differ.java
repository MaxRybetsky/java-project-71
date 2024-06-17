package hexlet.code;

import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2) {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String path1, String path2, String format) {
        Map<String, Object> fileData1 = FileDataFetcher.getDataFromFile(path1);
        Map<String, Object> fileData2 = FileDataFetcher.getDataFromFile(path2);

        List<Map<String, Object>> allEntries = EntryBuilder.build(fileData1, fileData2);

        return Formatter.formatEntries(allEntries, format);
    }
}
