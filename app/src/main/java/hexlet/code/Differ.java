package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Differ {

  private Differ() {
    throw new IllegalStateException("Utility class");
  }

  public static String generate(
          Map<String, Object> map1,
          Map<String, Object> map2,
          String format
  ) throws JsonProcessingException {
    if (map1 == null && map2 == null) {
      return "";
    }

    final Map<String, Object> data1 = map1 == null ? Map.of() : map1;
    final Map<String, Object> data2 = map2 == null ? Map.of() : map2;

    List<DiffEntry> diff = buildDiff(data1, data2);

    diff.sort(Comparator.comparing(DiffEntry::key));

    return Formatter.format(diff, format);
  }

  public static List<DiffEntry> buildDiff(Map<String, Object> data1, Map<String, Object> data2) {
    List<DiffEntry> diff = new ArrayList<>();
    Set<String> keys = new HashSet<>(data1.keySet());
    keys.addAll(data2.keySet());

    for (String key : keys) {
      if (!data2.containsKey(key)) { // if the key is only in data1
        diff.add(new DiffEntry(key, data1.get(key), null, DiffEntry.DiffStatus.REMOVED));
      } else if (!data1.containsKey(key)) { // if the key is only in data2
        diff.add(new DiffEntry(key, null, data2.get(key), DiffEntry.DiffStatus.ADDED));
      } else if (!Objects.equals(data1.get(key), data2.get(key))) { // if the key in data2 not equal to data1
        diff.add(new DiffEntry(key, data1.get(key), data2.get(key), DiffEntry.DiffStatus.MODIFIED));
      } else { // if the key is in data1 and data2 and equal
        diff.add(new DiffEntry(key, data1.get(key), data2.get(key), DiffEntry.DiffStatus.UNCHANGED));
      }
    }

    return diff;
  }

}
