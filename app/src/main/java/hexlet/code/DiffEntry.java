package hexlet.code;

public record DiffEntry(
        String key,
        Object oldValue,
        Object newValue,
        DiffStatus status
) {
  public DiffEntry {
    if (status == DiffStatus.ADDED && oldValue != null) {
      throw new IllegalArgumentException("ADDED entries should not have oldValue");
    }
    if (status == DiffStatus.REMOVED && newValue != null) {
      throw new IllegalArgumentException("REMOVED entries should not have newValue");
    }
  }

  public enum DiffStatus {
    ADDED,
    REMOVED,
    MODIFIED,
    UNCHANGED
  }
}
