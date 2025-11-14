package hexlet.code;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
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
            String diff = Differ.generate(data1, data2);
            System.out.println(diff);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    public static Path getPath(String filePath) {
        return Path.of(filePath).toAbsolutePath().normalize();
    }

    public static String readFile(Path path) throws IOException {
        return Files.readString(path);
    }

    public static Map<String, Object> getJsonData(String filePath) {
        Path path = getPath(filePath);

        String fileContent;
        try {
            fileContent = readFile(path);
        } catch (NoSuchFileException e) {
            throw new RuntimeException("File not found: " + filePath);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + filePath);
        }

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> unsortedJsonMap;
        try {
            unsortedJsonMap = objectMapper.readValue(fileContent, new TypeReference<>() {});
        } catch (JsonParseException e) {
            throw new RuntimeException("Invalid JSON format in file: " + filePath);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + filePath);
        }

        return new TreeMap<>(unsortedJsonMap);
    }
}
