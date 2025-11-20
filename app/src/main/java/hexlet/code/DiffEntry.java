package hexlet.code;

public record DiffEntry(
        String key,
        Object firstValue,
        Object secondValue,
        DiffStatus status
) {
  public DiffEntry {
    if (status == DiffStatus.ADDED && firstValue != null) {
      throw new IllegalArgumentException("ADDED entries should not have firstValue");
    }
    if (status == DiffStatus.REMOVED && secondValue != null) {
      throw new IllegalArgumentException("REMOVED entries should not have secondValue");
    }
  }

  public enum DiffStatus {
    ADDED,
    REMOVED,
    MODIFIED,
    UNCHANGED
  }
}
