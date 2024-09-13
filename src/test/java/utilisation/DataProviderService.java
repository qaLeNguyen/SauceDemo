package utilisation;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DataProviderService {

    @DataProvider(name = "productNames")
    public Object[][] productNames() {
        try {
            List<Map<String, String>> fullInfo = DataLoader.loadTestData(
                    "src/test/resources/data/product.json",
                    "productNames");

            Object[][] data = new Object[fullInfo.size()][1];

            for (int i = 0; i < fullInfo.size(); i++) {
                Map<String, String> value = fullInfo.get(i);
                data[i][0] = value.get("name");
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data", e);
        }
    }


}
