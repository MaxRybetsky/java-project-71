package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;
import java.util.function.Function;

public class DataConvertHandler {
    private static final Map<String, Function<String, Map<String, Object>>> CONVERTER_MAP = Map.of(
            "json", DataConvertHandler::convertJson,
            "yaml", DataConvertHandler::convertYaml,
            "yml", DataConvertHandler::convertYaml
    );

    public static Map<String, Object> convert(String content, String extension) {
        return CONVERTER_MAP.get(extension.toLowerCase()).apply(content);
    }

    private static Map<String, Object> convertJson(String content) {
        try {
            return new ObjectMapper().readValue(content, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, Object> convertYaml(String content) {
        try {
            return new ObjectMapper(new YAMLFactory()).readValue(content, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
