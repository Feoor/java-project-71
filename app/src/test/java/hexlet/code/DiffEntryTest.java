package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DiffEntryTest {

  /**
   * The DiffEntry class represents a single entry in a diff.
   */

  @Test
  public void testDummy() {
    DiffEntry entry = new DiffEntry(
            "key",
            "value1",
            "value1",
            DiffEntry.DiffStatus.UNCHANGED
    );

    assertNotNull(entry);
    assertEquals("key", entry.key());
    assertEquals("value1", entry.firstValue());
    assertEquals("value1", entry.secondValue());
    assertEquals(DiffEntry.DiffStatus.UNCHANGED, entry.status());
  }

  @Test
  public void testConstructorThrowsException() {
    Exception exception1 = assertThrows(IllegalArgumentException.class, () -> new DiffEntry(
            "key",
            "value1",
            "value2",
            DiffEntry.DiffStatus.ADDED
    ));

    assertEquals(
            "ADDED entries should not have firstValue",
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
            "REMOVED entries should not have secondValue",
            exception2.getMessage(),
            "Unexpected exception2 message"
    );
  }

}
