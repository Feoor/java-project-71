package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff", version = "gendiff 1.0", mixinStandardHelpOptions = true)
public final class App implements Runnable {

    @Option(
            names = { "-f", "--format" },
            defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]"
    )
    private String format;

    @Parameters(index = "0", description = "path to first file")
    private String file1;

    @Parameters(index = "1", description = "path to second file")
    private String file2;

    @Override
    public void run() {
        try {
            String diff = Differ.generate(file1, file2, format);
            System.out.println(diff);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
