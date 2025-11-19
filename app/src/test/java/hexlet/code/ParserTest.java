package hexlet.code;

import static hexlet.code.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;

class ParserTest {

  /**
   * The Parser class contains static methods for parsing files and strings
   * into maps of string keys and their corresponding object values.
   * The parse method takes a file path as input and produces a map.
   */

  @Test
  void testJsonParse() {
    // Arrange
    String path = "src/test/resources/fixtures/json1.json";

    // Act & Assert
    Map<String, Object> actual = parse(path);
    Map<String, Object> excepted = new TreeMap<>(Map.of(
            "follow", false,
            "host", "hexlet.io",
            "proxy", "123.234.53.22",
            "timeout", 50
    ));

    assertEquals(excepted, actual);
  }

  @Test
  void testYamlParse() {
    // Arrange
    String path = "src/test/resources/fixtures/yaml1.yml";

    // Act & Assert
    Map<String, Object> actual = parse(path);
    Map<String, Object> excepted = new TreeMap<>(Map.of(
            "host", "hexlet.io",
            "proxy", "987.654.321.098",
            "timeout", 50,
            "verbose", true
    ));

    assertEquals(excepted, actual);
  }

  @Test
  void testParseWithEmptyJsonFile() {
    // Arrange
    String filePath = "src/test/resources/fixtures/empty.json"; // Assume this file is empty

    // Act
    Map<String, Object> result = parse(filePath);

    // Assert
    assertNotNull(result, "The resulting map should not be null");
    assertTrue(result.isEmpty(), "The resulting map should be empty for an empty JSON file");
  }

  @Test
  void testParseWithNonExistentFile() {
    // Arrange
    String filePath = "src/test/resources/fixtures/nonexistent.json";

    // Assert
    Exception exception = assertThrows(RuntimeException.class, () -> {
      // Act
      parse(filePath);
    });

    assertTrue(exception.getMessage().contains("File not found:"),
            "Should throw an exception if the file does not exist");
  }

  @Test
  void testParseInvalidJsonFormat() {
    // Arrange
    String filePath = "src/test/resources/fixtures/invalidJsonFormat.json";

    // Assert
    Exception exception = assertThrows(RuntimeException.class, () -> {
      // Act
      parse(filePath);
    });

    assertTrue(exception.getMessage().contains("Invalid JSON format in file:"),
            "Should throw an exception if the JSON content is invalid");
  }

  @Test
  void testConstructor() {
    // Act & Assert
    IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            Parser::new,
            "Expected constructor to throw IllegalStateException"
    );
    assertEquals("Utility class", exception.getMessage());
  }
}