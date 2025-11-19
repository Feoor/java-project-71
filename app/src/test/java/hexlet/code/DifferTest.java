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
    String actual = Differ.generate(json1, json2);
    String excepted = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
    assertEquals(excepted, actual);

    actual = Differ.generate(json2, yaml1);
    excepted = """
                {
                    host: hexlet.io
                  - timeout: 20
                  + timeout: 50
                    verbose: true
                  + proxy: 987.654.321.098
                }""";
    assertEquals(excepted, actual);
  }

  @Test
  void testEmptyGenerate() {
    try {
      String actual = Differ.generate(null, null);
      String excepted = "";
      assertNotNull(actual);
      assertEquals(excepted, actual);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testGenerateWithOneEmpty() {
    String actual = Differ.generate(json1, null);
    String excepted = """
                {
                  - follow: false
                  - host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                }""";
    assertEquals(excepted, actual);

    String actual2 = Differ.generate(null, json2);
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