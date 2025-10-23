package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff", version = "gendiff 1.0", mixinStandardHelpOptions = true)
public class App implements Runnable {
    @Override
    public void run() {
        // business logic here
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute("--help");
        System.exit(exitCode);
    }
}
