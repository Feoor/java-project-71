package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Tests for the Formatter class.
 * The Formatter class contains utility methods for formatting lists of DiffEntry objects into specific string formats.
 * The main method being tested is the static format method, which supports different formatting styles
 * (currently only "stylish" is supported).
 */
class FormatterTest {

    private static List<DiffEntry> diffs;
    private static List<DiffEntry> emptyDiffs;

    @BeforeAll
    static void setUp() {
        diffs = List.of(
                new DiffEntry("key1", null, "newValue", DiffEntry.DiffStatus.ADDED),
                new DiffEntry("key2", "oldValue", null, DiffEntry.DiffStatus.REMOVED),
                new DiffEntry("key3", "oldValue", "newValue", DiffEntry.DiffStatus.MODIFIED),
                new DiffEntry("key4", List.of("a", "b", "c"), List.of("e", "f", "g"), DiffEntry.DiffStatus.MODIFIED),
                new DiffEntry("key5", "sameValue", "sameValue", DiffEntry.DiffStatus.UNCHANGED)
        );
        emptyDiffs = List.of();
    }

    @ParameterizedTest
    @MethodSource({"provideFormats"})
    void testFormatWithAllStatuses(String format, String expectedOutput) throws Exception {
        // Act
        String result = Formatter.format(diffs, format);

        // Assert
        assertEquals(expectedOutput, result);
    }

    static Stream<Arguments> provideFormats() {
        return Stream.of(
                Arguments.of("stylish", """
              {
                + key1: newValue
                - key2: oldValue
                - key3: oldValue
                + key3: newValue
                - key4: [a, b, c]
                + key4: [e, f, g]
                  key5: sameValue
              }"""),
                Arguments.of("plain", """
              Property 'key1' was added with value: 'newValue'
              Property 'key2' was removed
              Property 'key3' was updated. From 'oldValue' to 'newValue'
              Property 'key4' was updated. From [complex value] to [complex value]"""),
                Arguments.of("json", """
              {
                "diffs" : [ {
                  "key" : "key1",
                  "status" : "added",
                  "newValue" : "newValue"
                }, {
                  "key" : "key2",
                  "status" : "removed",
                  "oldValue" : "oldValue"
                }, {
                  "key" : "key3",
                  "status" : "modified",
                  "oldValue" : "oldValue",
                  "newValue" : "newValue"
                }, {
                  "key" : "key4",
                  "status" : "modified",
                  "oldValue" : [ "a", "b", "c" ],
                  "newValue" : [ "e", "f", "g" ]
                }, {
                  "key" : "key5",
                  "status" : "unchanged",
                  "oldValue" : "sameValue",
                  "newValue" : "sameValue"
                } ]
              }""")
        );
    }

    @Test
    void testFormatStylishWithEmptyDiffList() throws Exception  {
        // Arrange
        String expectedOutput = "{\n}";

        // Act
        String result = Formatter.format(emptyDiffs, "stylish");

        // Assert
        assertEquals(expectedOutput, result);
    }

    @Test
    void testFormatPlainWithEmptyDiffList() throws Exception  {
        // Arrange
        String expectedOutput = "";

        // Act
        String result = Formatter.format(emptyDiffs, "plain");

        // Assert
        assertEquals(expectedOutput, result);
    }

    @Test
    void testFormatJsonWithEmptyDiffList() throws Exception  {
        // Arrange
        String expectedOutput = """
            {
              "diffs" : [ ]
            }""";

        // Act
        String result = Formatter.format(emptyDiffs, "json");

        // Assert
        assertEquals(expectedOutput, result);
    }

    @Test
    void testFormatWithUnknownFormatThrowsException() {
        // Arrange
        List<DiffEntry> invalidDiff = List.of(
                new DiffEntry("key1", null, "value1", DiffEntry.DiffStatus.ADDED)
        );

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Formatter.format(
                invalidDiff,
                "unknown"
        ));
        assertEquals("Unknown format: unknown", exception.getMessage());
    }
}
