package hexlet.code;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Map;

public class Parser {
  private Parser() {
    throw new IllegalStateException("Utility class");
  }

  public static Map<String, Object> parse(String filePath) {
    Path path = getPath(filePath);
    String fileContent;

    try {
      fileContent = readFile(path);
    } catch (NoSuchFileException e) {
      throw new RuntimeException("File not found: " + filePath);
    } catch (IOException e) {
      throw new RuntimeException("Error reading file: " + filePath);
    }

    String fileExtension = getFileExtension(filePath).toLowerCase();
    ObjectMapper mapper = getObjectMapper(fileExtension);
    Map<String, Object> map;
    try {
      map = mapper.readValue(fileContent, new TypeReference<>() {});
    } catch (JsonParseException e) {
      throw new RuntimeException("Invalid JSON format in file: " + filePath);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error mapping JSON in file: " + filePath);
    }

    return map;
  }

  public static Path getPath(String filePath) {
    return Path.of(filePath).toAbsolutePath().normalize();
  }

  public static String readFile(Path path) throws IOException {
    return Files.readString(path);
  }

  public static String getFileExtension(String filePath) {
    return filePath.substring(filePath.lastIndexOf('.') + 1);
  }

  public static ObjectMapper getObjectMapper(String fileExtension) {
    switch (fileExtension) {
      case "json" -> {
        return new JsonMapper();
      }
      case "yml", "yaml" -> {
        return new YAMLMapper();
      }
      default -> throw new IllegalArgumentException("Unsupported file format: " + fileExtension);
    }
  }
}
