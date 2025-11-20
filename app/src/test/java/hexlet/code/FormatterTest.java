package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Formatter class.
 * The Formatter class contains utility methods for formatting lists of DiffEntry objects into specific string formats.
 * The main method being tested is the static format method, which supports different formatting styles
 * (currently only "stylish" is supported).
 */
class FormatterTest {

  @Test
  void testFormatStylishWithAllStatuses() {
    // Arrange
    List<DiffEntry> diffs = List.of(
            new DiffEntry("key1", null, "newValue", DiffEntry.DiffStatus.ADDED),
            new DiffEntry("key2", "oldValue", null, DiffEntry.DiffStatus.REMOVED),
            new DiffEntry("key3", "oldValue", "newValue", DiffEntry.DiffStatus.MODIFIED),
            new DiffEntry("key4", "sameValue", "sameValue", DiffEntry.DiffStatus.UNCHANGED)
    );
    String expectedOutput = """
            {
              + key1: newValue
              - key2: oldValue
              - key3: oldValue
              + key3: newValue
                key4: sameValue
            }""";

    // Act
    String result = Formatter.format(diffs, "stylish");

    // Assert
    assertEquals(expectedOutput, result);
  }

  @Test
  void testFormatStylishWithEmptyDiffList() {
    // Arrange
    List<DiffEntry> emptyDiffs = List.of();
    String expectedOutput = "{\n}";

    // Act
    String result = Formatter.format(emptyDiffs, "stylish");

    // Assert
    assertEquals(expectedOutput, result);
  }

  @Test
  void testFormatWithUnknownFormatThrowsException() {
    // Arrange
    List<DiffEntry> diffs = List.of(
            new DiffEntry("key1", null, "value1", DiffEntry.DiffStatus.ADDED)
    );

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Formatter.format(
            diffs,
            "unknown"
    ));
    assertEquals("Unknown format: unknown", exception.getMessage());
  }

  @Test
  void testConstructorThrowsException() {
    // Act & Assert
    IllegalStateException exception = assertThrows(IllegalStateException.class, Formatter::new);
    assertEquals("Utility class", exception.getMessage());
  }
}