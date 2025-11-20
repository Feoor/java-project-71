package hexlet.code;

import static hexlet.code.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DifferTest {

  private static final String FORMAT = "stylish";
  private static Map<String, Object> json1;
  private static Map<String, Object> json2;
  private static Map<String, Object> yaml1;

  @BeforeAll
  static void setUp() {
    json1 = parse("src/test/resources/fixtures/json1.json");
    json2 = parse("src/test/resources/fixtures/json2.json");
    yaml1 = parse("src/test/resources/fixtures/yaml1.yml");
  }

  @Test
  void testGenerate() {
    // Act & Assert
    String actual1 = Differ.generate(json1, json2, FORMAT);
    String excepted1 = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

    assertEquals(excepted1, actual1);

    // Act & Assert
    String actual2 = Differ.generate(json2, yaml1, FORMAT);
    String excepted2 = """
                {
                    host: hexlet.io
                  + proxy: 987.654.321.098
                  - timeout: 20
                  + timeout: 50
                    verbose: true
                }""";

    assertEquals(excepted2, actual2);

    // Arrange
    Map<String, Object> json3 = parse("src/test/resources/fixtures/json3.json");
    Map<String, Object> yaml2 = parse("src/test/resources/fixtures/yaml2.yml");

    // Act & Assert
    String actual3 = Differ.generate(json3, yaml2, FORMAT);
    String excepted3 = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";

    assertEquals(excepted3, actual3);
  }

  @Test
  void testEmptyGenerate() {
    try {
      String actual = Differ.generate(null, null, FORMAT);
      String excepted = "";
      assertNotNull(actual);
      assertEquals(excepted, actual);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testGenerateWithOneEmpty() {
    String actual = Differ.generate(json1, null, FORMAT);
    String excepted = """
                {
                  - follow: false
                  - host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                }""";
    assertEquals(excepted, actual);

    String actual2 = Differ.generate(null, json2, FORMAT);
    String excepted2 = """
                {
                  + host: hexlet.io
                  + timeout: 20
                  + verbose: true
                }""";
    assertEquals(excepted2, actual2);
  }

  @Test
  void testDifferClass() {
    Exception exception = assertThrows(
            IllegalStateException.class,
            Differ::new,
            "Expected constructor to throw IllegalStateException"
    );

    assertTrue(exception.getMessage().contains("Utility class"));
  }
}