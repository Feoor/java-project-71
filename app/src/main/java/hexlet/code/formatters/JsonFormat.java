package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hexlet.code.DiffEntry;
import java.util.List;

public final class JsonFormat implements Format {
    private JsonFormat() {
        throw new IllegalStateException("Utility class");
    }

    public static String format(List<DiffEntry> diff) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonNodes = mapper.createObjectNode();
        ArrayNode arrayNode = mapper.createArrayNode();
        jsonNodes.set("diffs", arrayNode);

        for (DiffEntry entry : diff) {
            ObjectNode node = mapper.createObjectNode();
            node.put("key", entry.key());
            node.put("status", entry.status().toString().toLowerCase());

            if (entry.oldValue() != null) {
                node.putPOJO("oldValue", entry.oldValue());
            }
            if (entry.newValue() != null) {
                node.putPOJO("newValue", entry.newValue());
            }

            arrayNode.add(node);
        }

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNodes);
    }
}
