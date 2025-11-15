package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;

class AppTest {

  /**
   * Test for the getJsonData method in the App class.
   * This method parses a JSON file into a TreeMap, ensuring
   * that the JSON content is properly serialized and sorted by keys.
   */

  @Test
  void testGetJsonDataWithValidFile() {
    // Arrange
    String filePath = "src/test/resources/fixtures/file1.json";

    // Act
    Map<String, Object> result = App.getJsonData(filePath);

    // Assert
    assertNotNull(result, "The resulting map should not be null");
    assertFalse(result.isEmpty(), "The resulting map should not be empty");
    assertInstanceOf(TreeMap.class, result, "The resulting map should be an instance of TreeMap");
    assertEquals("hexlet.io", result.get("host"),
            "Key 'host' should have the correct associated value");
    assertEquals(false, result.get("follow"),
            "Key 'follow' should have the correct associated value");
  }

  @Test
  void testGetJsonDataWithEmptyJsonFile() {
    // Arrange
    String filePath = "src/test/resources/fixtures/empty.json"; // Assume this file is empty

    // Act
    Map<String, Object> result = App.getJsonData(filePath);

    // Assert
    assertNotNull(result, "The resulting map should not be null");
    assertTrue(result.isEmpty(), "The resulting map should be empty for an empty JSON file");
  }

  @Test
  void testGetJsonDataWithNonExistentFile() {
    // Arrange
    String filePath = "src/test/resources/fixtures/nonexistent.json";

    // Assert
    Exception exception = assertThrows(RuntimeException.class, () -> {
      // Act
      App.getJsonData(filePath);
    });

    assertTrue(exception.getMessage().contains("File not found:"),
            "Should throw an exception if the file does not exist");
  }

  @Test
  void testGetJsonDataWithInvalidJsonFormat() {
    // Arrange
    String filePath = "src/test/resources/fixtures/invalidJsonFormat.json";

    // Assert
    Exception exception = assertThrows(RuntimeException.class, () -> {
      // Act
      App.getJsonData(filePath);
    });

    assertTrue(exception.getMessage().contains("Invalid JSON format in file:"),
            "Should throw an exception if the JSON content is invalid");
  }
}