package hexlet.code;

import java.util.List;
import hexlet.code.formatters.StylishFormat;
import hexlet.code.formatters.PlainFormat;

public class Formatter {

  public Formatter() {
    throw new IllegalStateException("Utility class");
  }

  public static String format(List<DiffEntry> diff, String format) {
    return switch (format.toLowerCase()) {
      case "stylish" -> StylishFormat.format(diff);
      case "plain" -> PlainFormat.format(diff);
      default -> throw new IllegalArgumentException("Unknown format: " + format);
    };
  }
}