package hexlet.code.formatters;

import hexlet.code.DiffEntry;
import java.util.List;

public class StylishFormat implements Format {
  private StylishFormat() {
    throw new IllegalStateException("Utility class");
  }

  public static String format(List<DiffEntry> diff) {
    StringBuilder sb = new StringBuilder("{\n");

    for (DiffEntry entry : diff) {
      switch (entry.status()) {
        case ADDED -> sb.append("  + ").append(entry.key()).append(": ").append(entry.newValue()).append("\n");
        case REMOVED -> sb.append("  - ").append(entry.key()).append(": ").append(entry.oldValue()).append("\n");
        case MODIFIED -> {
          sb.append("  - ").append(entry.key()).append(": ").append(entry.oldValue()).append("\n");
          sb.append("  + ").append(entry.key()).append(": ").append(entry.newValue()).append("\n");
        }
        case UNCHANGED -> sb.append("    ").append(entry.key()).append(": ").append(entry.oldValue()).append("\n");
        default -> throw new IllegalStateException("Unexpected value: " + entry.status());
      }
    }

    return sb.append("}").toString();
  }
}