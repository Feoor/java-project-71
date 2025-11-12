package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Map;
import java.util.TreeMap;

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
        try {
            Map<String, Object> data1 = getJsonData(file1);
            Map<String, Object> data2 = getJsonData(file2);
            System.out.println("Done");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute("src/test/resources/fixtures/file1.json", "src/test/resources/fixtures/file3.json");
        System.exit(exitCode);
    }

    public static Path getPath(String filePath) {
        return Path.of(filePath).toAbsolutePath().normalize();
    }

    public static String readFile(Path path) throws Exception {
        return Files.readString(path);
    }

    public static Map<String, Object> getJsonData(String filePath) throws Exception {
        Path path = getPath(filePath);
        String fileContent = readFile(path);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> unsortedJsonMap = objectMapper.readValue(fileContent, new TypeReference<Map<String, Object>>() {});
        return new TreeMap<>(unsortedJsonMap);
    }
}
