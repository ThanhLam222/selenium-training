package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    public static Object[][] getDataProvider(String filePath) {

        try {

            ObjectMapper mapper = new ObjectMapper();

            List<Map<String, Object>> list =
                    mapper.readValue(new File(filePath), List.class);

            List<Object[]> data = new ArrayList<>();

            for (Map<String, Object> record : list) {
                data.add(record.values().toArray());
            }

            return data.toArray(new Object[0][]);

        } catch (Exception e) {
            throw new RuntimeException("Cannot read JSON file", e);
        }
    }
}
