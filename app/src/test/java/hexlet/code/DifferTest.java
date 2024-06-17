package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    @Test
    public void whenGivenTwoJsonFilesThenGenerateDifferencesBetweenThem() throws URISyntaxException, IOException {
        // given
        String file1 = getAbsoluteFilePath("fixtures/given_file1.json");
        String file2 = getAbsoluteFilePath("fixtures/given_file2.json");
        String expected = readFile("fixtures/expected_json.txt");

        // when
        String result = Differ.generate(file1, file2);

        // then
        assertEquals(expected, result);
    }

    private String getAbsoluteFilePath(String path) throws URISyntaxException {
        return Paths.get(getClass().getClassLoader().getResource(path).toURI()).toString();
    }

    private String readFile(String path) throws IOException {
        return new String(
                getClass()
                        .getClassLoader()
                        .getResourceAsStream(path)
                        .readAllBytes()
        );
    }


}
