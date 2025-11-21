package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DiffEntryTest {

  /**
   * The DiffEntry class represents a single entry in a diff.
   */

  @Test
  void testDummy() {
    DiffEntry entry = new DiffEntry(
            "key",
            "value1",
            "value1",
            DiffEntry.DiffStatus.UNCHANGED
    );

    assertNotNull(entry);
    assertEquals("key", entry.key());
    assertEquals("value1", entry.oldValue());
    assertEquals("value1", entry.newValue());
    assertEquals(DiffEntry.DiffStatus.UNCHANGED, entry.status());
  }

  @Test
  void testConstructorThrowsException() {
    Exception exception1 = assertThrows(IllegalArgumentException.class, () -> new DiffEntry(
            "key",
            "value1",
            "value2",
            DiffEntry.DiffStatus.ADDED
    ));

    assertEquals(
            "ADDED entries should not have oldValue",
            exception1.getMessage(),
            "Unexpected exception2 message"
    );

    Exception exception2 = assertThrows(IllegalArgumentException.class, () -> new DiffEntry(
            "key",
            "value1",
            "value2",
            DiffEntry.DiffStatus.REMOVED
    ));

    assertEquals(
            "REMOVED entries should not have newValue",
            exception2.getMessage(),
            "Unexpected exception2 message"
    );
  }

}
