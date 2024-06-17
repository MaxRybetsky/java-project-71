package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {
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
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        String result = Differ.generate(path1, path2, format);
        System.out.println(result);
        return 0;
    }
}
