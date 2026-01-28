package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop = new Properties();

    public static void loadConfig(String exercise, boolean isCI) {
        prop.clear();

        loadFile("common.properties");
        loadFile(exercise + ".properties");
        if (isCI) {
            loadFile("ci.properties");
        }
    }

    private static void loadFile(String path) {
        try {
            InputStream inp = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream(path);

            if (inp == null) {
                throw new RuntimeException("Cannot find config file: " + path);
            }

            prop.load(inp);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load " + path, e);
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(prop.getProperty(key));
    }
}
