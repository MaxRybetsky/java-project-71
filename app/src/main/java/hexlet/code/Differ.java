package hexlet.code;

import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String format, String path2, String path1) {
        Map<String, Object> fileData1 = FileDataFetcher.getDataFromFile(path1);
        Map<String, Object> fileData2 = FileDataFetcher.getDataFromFile(path2);

        List<Map<EntryValueName, Object>> allEntries = EntryBuilder.build(fileData1, fileData2);

        return DiffResultBuilder.formatEntries(allEntries, format);
    }
}
