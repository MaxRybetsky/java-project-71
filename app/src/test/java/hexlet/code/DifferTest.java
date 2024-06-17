package hexlet.code;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml", "yml"})
    public void whenGivenTwoFilesThenGenerateDifferencesBetweenThem(
            String extension
    ) throws URISyntaxException, IOException {
        // given
        String file1 = getAbsoluteFilePath("fixtures/given_file1." + extension);
        String file2 = getAbsoluteFilePath("fixtures/given_file2." + extension);

        String expectedStylish = readFile("fixtures/expected_stylish.txt");
        String expectedPlain = readFile("fixtures/expected_plain.txt");

        // when
        String actualStylish = Differ.generate("stylish", file1, file2);
        String actualPlain = Differ.generate("plain", file1, file2);

        // then
        assertEquals(expectedStylish, actualStylish);
        assertEquals(expectedPlain, actualPlain);
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
