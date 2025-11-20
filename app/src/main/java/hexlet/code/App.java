package hexlet.code;

import java.util.Map;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff", version = "gendiff 1.0", mixinStandardHelpOptions = true)
public class App implements Runnable {

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
      Map<String, Object> data1 = Parser.parse(file1);
      Map<String, Object> data2 = Parser.parse(file2);
      String diff = Differ.generate(data1, data2, format);
      System.out.println(diff);
    } catch (RuntimeException e) {
      System.err.println("Error: " + e.getMessage());
      System.exit(1);
    } catch (Exception e) {
      System.err.println("Unexpected error: " + e.getMessage());
      e.printStackTrace();
      System.exit(2);
    }
  }

  public static void main(String[] args) {
    int exitCode = new CommandLine(new App()).execute(args);
    System.exit(exitCode);
  }
}
