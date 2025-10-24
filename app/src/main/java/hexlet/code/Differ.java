package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Differ {

//    public static Path getPath(String path) {
//        return Path.of(path);
//    }

    public static String getContent(String path) throws Exception {
        Path filePath = Path.of(path);
        return Files.readString(filePath);
    }

    public static Map getData(String content) throws Exception {
        return null;
    }

    public static String diff(Map data1, Map data2) {
        return null;
    }

}
