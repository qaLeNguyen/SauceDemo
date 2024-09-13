package utilisation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DataLoader {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<Map<String, String>> loadTestData(String filePath, String category) throws IOException {
        JsonNode rootNode = mapper.readTree(new File(filePath));
        JsonNode categoryNode = rootNode.path(category);

        List<Map<String, String>> dataList = new ArrayList<>();
        Iterator<JsonNode> elements = categoryNode.elements();
        while (elements.hasNext()) {
            JsonNode element = elements.next();
            dataList.add(mapper.convertValue(element, Map.class));
        }
        return dataList;
    }
}