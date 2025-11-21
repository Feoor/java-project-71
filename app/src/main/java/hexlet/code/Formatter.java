package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.JsonFormat;
import hexlet.code.formatters.PlainFormat;
import hexlet.code.formatters.StylishFormat;
import java.util.List;

public final class Formatter {

    private Formatter() {
        throw new IllegalStateException("Utility class");
    }

    public static String format(List<DiffEntry> diff, String format) throws JsonProcessingException {
        return switch (format.toLowerCase()) {
            case "stylish" -> StylishFormat.format(diff);
            case "plain" -> PlainFormat.format(diff);
            case "json" -> JsonFormat.format(diff);
            default -> throw new IllegalArgumentException("Unknown format: " + format);
        };
    }
}
