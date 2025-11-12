package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DifferTest {

    private static Map<String, Object> data1;
    private static Map<String, Object> data2;
    private static Map<String, Object> data3;

    @BeforeAll
    static void setUp() throws Exception {
        data1 = App.getJsonData("src/test/resources/fixtures/file1.json");
        data2 = App.getJsonData("src/test/resources/fixtures/file2.json");
        data3 = App.getJsonData("src/test/resources/fixtures/file3.json");
    }

    @Test
    void testDiff() throws Exception {
        String actual = Differ.diff(data1, data2);
        String excepted = "{\n" +
                "  - follow: false\n" +
                "    host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "  + timeout: 20\n" +
                "  + verbose: true\n" +
                "}";
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
    void testEmptyDiff() {
        try {
            String actual = Differ.diff(null, null);
            String excepted = "";
            assertEquals(excepted, actual);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDiffWithOneEmpty() throws Exception {
        String actual = Differ.diff(data1, null);
        String excepted = "{\n" +
                "  - follow: false\n" +
                "  - host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "}";
        assertEquals(excepted, actual);
    }
}