package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;

public class FileDataFetcher {
    public static Map<String, Object> getDataFromFile(String filePath) {
        Path path = Paths.get(filePath)
                .toAbsolutePath()
                .normalize();

        String content = getFileContent(path);
        String extension = getExtension(filePath);

        return convertToMap(content, extension);
    }

    private static Map<String, Object> convertToMap(String content, String extension) {
        return DataConvertHandler.convert(content, extension);
    }

    private static String getFileContent(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            System.out.println("Error with reading file content!");
            throw new RuntimeException(e);
        }
    }

    public static String getExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1))
                .orElseThrow();
    }
}
