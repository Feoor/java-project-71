package hexlet.code;

import java.util.List;

public class Formatter {

  public Formatter() {
    throw new IllegalStateException("Utility class");
  }

  public static String format(List<DiffEntry> diff, String format) {
    return switch (format) {
      case "stylish" -> formatStylish(diff);
      default -> throw new IllegalArgumentException("Unknown format: " + format);
    };
  }

  private static String formatStylish(List<DiffEntry> diff) {
    StringBuilder sb = new StringBuilder("{\n");

    for (DiffEntry entry : diff) {
      switch (entry.status()) {
        case ADDED -> sb.append("  + ").append(entry.key()).append(": ").append(entry.secondValue()).append("\n");
        case REMOVED -> sb.append("  - ").append(entry.key()).append(": ").append(entry.firstValue()).append("\n");
        case MODIFIED -> {
          sb.append("  - ").append(entry.key()).append(": ").append(entry.firstValue()).append("\n");
          sb.append("  + ").append(entry.key()).append(": ").append(entry.secondValue()).append("\n");
        }
        case null, default ->
                sb.append("    ").append(entry.key()).append(": ").append(entry.firstValue()).append("\n");
      }
    }

    return sb.append("}").toString();
  }
}