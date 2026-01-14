package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;

    public static void loadConfig() {
        try {
            prop = new Properties();
            FileInputStream inp = new FileInputStream("src/test/resources/config.properties");
            prop.load(inp);
        } catch (Exception e) {
            System.out.println("Error when read config file: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        if (prop == null) loadConfig();
        return prop.getProperty(key);
    }
}
