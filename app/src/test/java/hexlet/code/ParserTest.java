package hexlet.code;

import static hexlet.code.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
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
    String path1 = "src/test/resources/fixtures/json1.json";

    // Act & Assert
    Map<String, Object> actual1 = parse(path1);
    Map<String, Object> excepted1 = new TreeMap<>(Map.of(
            "follow", false,
            "host", "hexlet.io",
            "proxy", "123.234.53.22",
            "timeout", 50
    ));

    assertEquals(excepted1, actual1);

    // Arrange
    String path2 = "src/test/resources/fixtures/json2.json";

    // Act & Assert
    Map<String, Object> expectedMap = new HashMap<>();
    expectedMap.put("setting1", "Some value");
    expectedMap.put("setting2", 200);
    expectedMap.put("setting3", true);
    expectedMap.put("key1", "value1");
    expectedMap.put("numbers1", List.of(1, 2, 3, 4));
    expectedMap.put("numbers2", List.of(2, 3, 4, 5));
    expectedMap.put("id", 45);
    expectedMap.put("default", null);
    expectedMap.put("checked", false);
    expectedMap.put("numbers3", List.of(3, 4, 5));
    expectedMap.put("chars1", List.of("a", "b", "c"));
    expectedMap.put("chars2", List.of("d", "e", "f"));


    Map<String, Object> actual2 = parse(path2);
    Map<String, Object> excepted2 = new TreeMap<>(expectedMap);

    assertEquals(excepted2, actual2);
  }

  @Test
  void testYamlParse() {
    // Arrange
    String path1 = "src/test/resources/fixtures/yaml1.yml";

    // Act & Assert
    Map<String, Object> actual1 = parse(path1);
    Map<String, Object> excepted1 = new TreeMap<>(Map.of(
            "host", "hexlet.io",
            "proxy", "987.654.321.098",
            "timeout", 50,
            "verbose", true
    ));

    assertEquals(excepted1, actual1);

    // Arrange
    String path2 = "src/test/resources/fixtures/yaml2.yml";

    // Act & Assert
    Map<String, Object> expectedMap = new HashMap<>();
    expectedMap.put("setting1", "Another value");
    expectedMap.put("setting2", 300);
    expectedMap.put("setting3", "none");
    expectedMap.put("key2", "value2");
    expectedMap.put("numbers1", List.of(1, 2, 3, 4));
    expectedMap.put("numbers2", List.of(22, 33, 44, 55));
    expectedMap.put("id", null);
    expectedMap.put("default", List.of("value1", "value2"));
    expectedMap.put("checked", true);
    expectedMap.put("numbers4", List.of(4, 5, 6));
    expectedMap.put("chars1", List.of("a", "b", "c"));
    expectedMap.put("chars2", false);
    expectedMap.put("obj1", Map.of("nestedKey", "value", "isNested", true));

    Map<String, Object> actual2 = parse(path2);
    Map<String, Object> excepted2 = new TreeMap<>(expectedMap);

    assertEquals(excepted2, actual2);
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
  void testParseUnsupportedFileExtension() {
    // Arrange
    String filePath = "src/test/resources/fixtures/invalidFile.nae";

    // Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      // Act
      parse(filePath);
    });

    assertTrue(exception.getMessage().contains("Unsupported file format:"),
            "Should throw an exception if the file extension is not supported");
  }
}