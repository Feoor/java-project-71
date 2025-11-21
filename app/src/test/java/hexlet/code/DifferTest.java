package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DifferTest {

    private static String defaultFormat;
    private static String json1;
    private static String json2;
    private static String yaml1;
    private static String yaml2;

    @BeforeAll
    static void setUp() {
        defaultFormat = "stylish";
        json1 = "src/test/resources/fixtures/json1.json";
        json2 = "src/test/resources/fixtures/json2.json";
        yaml1 = "src/test/resources/fixtures/yaml1.yml";
        yaml2 = "src/test/resources/fixtures/yaml2.yml";
    }

    @Test
    void testGenerateWithStylishFormat() {
        String format = "stylish";

        // Act & Assert
        String actual1 = Differ.generate(json1, yaml1, format);
        String excepted1 = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  + proxy: 987.654.321.098
                    timeout: 50
                  + verbose: true
                }""";

        assertEquals(excepted1, actual1);

        // Act & Assert
        String actual2 = Differ.generate(json2, yaml2, format);
        String excepted2 = """
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

        assertEquals(excepted2, actual2);
    }

    @Test
    void testGenerateWithPlainFormat() {
        String format = "plain";

        // Act & Assert
        String actual1 = Differ.generate(json1, yaml1, format);
        String excepted1 = """
                Property 'follow' was removed
                Property 'proxy' was updated. From '123.234.53.22' to '987.654.321.098'
                Property 'verbose' was added with value: true""";

        assertEquals(excepted1, actual1);

        // Act & Assert
        String actual2 = Differ.generate(json2, yaml2, format);
        String excepted2 = """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From '45' to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From '200' to '300'
            Property 'setting3' was updated. From true to 'none'""";

        assertEquals(excepted2, actual2);
    }

    @Test
    void testGenerateWithJsonFormat() {
        String format = "json";

        // Act & Assert
        String actual1 = Differ.generate(json1, yaml1, format);
        String excepted1 = """
                {
                  "diffs" : [ {
                    "key" : "follow",
                    "status" : "removed",
                    "oldValue" : false
                  }, {
                    "key" : "host",
                    "status" : "unchanged",
                    "oldValue" : "hexlet.io",
                    "newValue" : "hexlet.io"
                  }, {
                    "key" : "proxy",
                    "status" : "modified",
                    "oldValue" : "123.234.53.22",
                    "newValue" : "987.654.321.098"
                  }, {
                    "key" : "timeout",
                    "status" : "unchanged",
                    "oldValue" : "50",
                    "newValue" : "50"
                  }, {
                    "key" : "verbose",
                    "status" : "added",
                    "newValue" : true
                  } ]
                }""";

        assertEquals(excepted1, actual1);
    }

    @Test
    void testEmptyGenerate() {
        try {
            String actual = Differ.generate(null, null, defaultFormat);
            String excepted = "";
            assertNotNull(actual);
            assertEquals(excepted, actual);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGenerateWithOneEmpty() {
        String actual = Differ.generate(json1, null, defaultFormat);
        String excepted = """
                {
                  - follow: false
                  - host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                }""";
        assertEquals(excepted, actual);

        String actual2 = Differ.generate(null, yaml1, defaultFormat);
        String excepted2 = """
                {
                  + host: hexlet.io
                  + proxy: 987.654.321.098
                  + timeout: 50
                  + verbose: true
                }""";
        assertEquals(excepted2, actual2);
    }
}
