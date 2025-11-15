package hexlet.code;

import java.util.Map;
import java.util.Objects;

public class Differ {

  public static String generate(Map<String, Object> map1, Map<String, Object> map2) {
    if (map1 == null && map2 == null) {
      return "";
    }

    final Map<String, Object> data1 = map1 == null ? Map.of() : map1;
    final Map<String, Object> data2 = map2 == null ? Map.of() : map2;

    StringBuilder result = new StringBuilder("{\n");

    // Bypass of keys that are not in the second comparison object,
    // as well as the difference in values
    data1.keySet().forEach(key -> {
      Object value1 = data1.get(key);
      Object value2 = data2.get(key);

      if (!data2.containsKey(key)) {
        result.append("  - ").append(key).append(": ").append(value1).append("\n");
      } else if (!Objects.equals(value1, value2)) {
        result.append("  - ").append(key).append(": ").append(value1).append("\n");
        result.append("  + ").append(key).append(": ").append(value2).append("\n");
      } else {
        result.append("    ").append(key).append(": ").append(value1).append("\n");
      }
    });

    // Bypass of keys that are not in the first comparison object
    data2.keySet().stream()
            .filter(key -> !data1.containsKey(key))
            .forEach((key) -> {
              result.append("  + ").append(key).append(": ").append(data2.get(key)).append("\n");
            });

    return result.append("}").toString();
  }

}
