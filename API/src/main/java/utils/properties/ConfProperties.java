package utils.properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfProperties {
    private static final Properties commonProperties;
    private static final Logger logger = LogManager.getLogger(ConfProperties.class);

    static {
        commonProperties = loadProperties("src/test/resources/conf.properties");
    }

    public static String getCommonProperty(String key) {
        return commonProperties.getProperty(key);
    }

    public static boolean getCommonBoolProperty(String key) {
        return Boolean.parseBoolean(commonProperties.getProperty(key));
    }

    public static int getCommonIntProperty(String key) {
        return Integer.parseInt(commonProperties.getProperty(key));
    }

    private static Properties loadProperties(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties;
        } catch (IOException e) {
            logger.error("Ошибка чтения файла конфига Properties", e);
            throw new RuntimeException("Failed to load properties from file: " + filePath, e);
        }
    }
}
