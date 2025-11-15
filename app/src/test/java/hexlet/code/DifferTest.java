package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DifferTest {

  private static Map<String, Object> data1;
  private static Map<String, Object> data2;
  private static Map<String, Object> data3;

  @BeforeAll
  static void setUp() {
    data1 = App.getJsonData("src/test/resources/fixtures/file1.json");
    data2 = App.getJsonData("src/test/resources/fixtures/file2.json");
    data3 = App.getJsonData("src/test/resources/fixtures/file3.json");
  }

  @Test
  void testGenerate() {
    String actual = Differ.generate(data1, data2);
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

    actual = Differ.generate(data2, data3);
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
    String actual = Differ.generate(data1, null);
    String excepted = """
                {
                  - follow: false
                  - host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                }""";
    assertEquals(excepted, actual);

    String actual2 = Differ.generate(null, data2);
    String excepted2 = """
                {
                  + host: hexlet.io
                  + timeout: 20
                  + verbose: true
                }""";
    assertEquals(excepted2, actual2);
  }
}