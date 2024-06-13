package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "Compares two configuration files and shows a difference.")
public class App {
    @Parameters(
            paramLabel = "filepath1",
            description = "path to first file",
            index = "0"
    )
    private String path1;

    @Parameters(
            paramLabel = "filepath2",
            description = "path to second file",
            index = "1"
    )
    private String path2;

    @Option(
            names = {"-f", "--format"},
            paramLabel = "format",
            defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]"
    )
    private String format;

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }
}
