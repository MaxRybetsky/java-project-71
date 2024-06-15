package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class FileDataFetcher {
    public static Map<String, Object> getDataFromFile(String filePath) {
        Path path = Paths.get(filePath)
                .toAbsolutePath()
                .normalize();

        String content = getFileContent(path);

        return convertToMap(content);
    }

    private static Map<String, Object> convertToMap(String content) {
        try {
            return new ObjectMapper().readValue(content, Map.class);
        } catch (JsonProcessingException e) {
            System.out.println("Error with converting JSON to map!");
            throw new RuntimeException(e);
        }
    }

    private static String getFileContent(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            System.out.println("Error with reading file content!");
            throw new RuntimeException(e);
        }
    }

}
