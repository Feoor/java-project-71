package hexlet.code.formatters;

import hexlet.code.DiffEntry;

import java.util.List;
import java.util.Map;

public class PlainFormat implements Format {

  public static String format(List<DiffEntry> diff) {
    StringBuilder sb = new StringBuilder();

    for (DiffEntry entry : diff) {
      switch (entry.status()) {
        case ADDED -> sb.append("Property ")
                .append(getFormattedKey(entry.key()))
                .append(" was added with value: ")
                .append(getFormattedValue(entry.secondValue()))
                .append("\n");
        case REMOVED -> sb.append("Property ")
                .append(getFormattedKey(entry.key()))
                .append(" was removed")
                .append("\n");
        case MODIFIED -> sb.append("Property ")
                .append(getFormattedKey(entry.key()))
                .append(" was updated. From ")
                .append(getFormattedValue(entry.firstValue()))
                .append(" to ")
                .append(getFormattedValue(entry.secondValue()))
                .append("\n");
        case UNCHANGED -> {
          // Do nothing for unchanged entries
        }
        default -> throw new IllegalStateException("Unexpected value: " + entry.status());
      }
    }

    if (!sb.isEmpty()) {
      sb.setLength(sb.length() - 1);
    }

    return sb.toString();
  }

  private static String getFormattedKey(String key) {
    return "'" + key + "'";
  }

  private static String getFormattedValue(Object value) {
    if (value instanceof List || value instanceof Map) {
      return "[complex value]";
    }

    if (value == null) {
      return "null";
    }

    if (value instanceof String) {
      return "'" + value + "'";
    }

    return String.valueOf(value);
  }
}
