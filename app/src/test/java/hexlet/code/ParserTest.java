package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    Map<String, Object> actual = Parser.parse(path);
    Map<String, Object> excepted = Map.of(
            "host", "hexlet.io",
            "timeout", 50,
            "proxy", "123.234.53.22",
            "follow", false
    );

    assertEquals(excepted, actual);
  }

  @Test
  void testYamlParse() {
    // Arrange
    String path = "src/test/resources/fixtures/yaml1.yml";

    // Act & Assert
    Map<String, Object> actual = Parser.parse(path);
    Map<String, Object> excepted = Map.of(
            "host", "hexlet.io",
            "verbose", true,
            "timeout", 50,
            "proxy", "123.234.53.22"
    );

    assertEquals(excepted, actual);
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