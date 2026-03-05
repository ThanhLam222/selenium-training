package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    public static Object[][] getDataProvider(String filePath) {

        List<Object[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            boolean header = true;

            while ((line = br.readLine()) != null) {

                if (header) {
                    header = false;
                    continue; // bỏ qua header
                }

                String[] values = line.split(",", -1); // keep empty value at the end
                String username = values[0];
                String password = values[1];
                boolean expected = Boolean.parseBoolean(values[2]); // convert String to boolean

                data.add(new Object[]{username, password, expected});
            }

        } catch (Exception e) {
            throw new RuntimeException("Cannot read CSV file", e);
        }

        return data.toArray(new Object[0][]);
    }
}