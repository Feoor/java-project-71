package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff", version = "gendiff 1.0", mixinStandardHelpOptions = true)
public class App implements Runnable {

    @Option(names = { "-f", "--format" }, description = "output format [default: stylish]")
    String format = "stylish";

    @Parameters(index = "0", description = "path to first file")
    private String file1;

    @Parameters(index = "1", description = "path to second file")
    private String file2;

    @Override
    public void run() {
        System.out.println("Formatting with " + format + " format");
        System.out.println("Comparing '" + file1 + "' with '" + file2 + "'");
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute("--help");
        System.exit(exitCode);
    }
}
