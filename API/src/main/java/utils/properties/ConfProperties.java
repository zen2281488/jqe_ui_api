package utils.properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfProperties {
    private static final Properties commonProperties;
    private static final Properties privateProperties;
    private static final Logger logger = LogManager.getLogger(RuntimeException.class);

    static {
        commonProperties = loadProperties("src/test/resources/conf.properties");
        privateProperties = loadProperties("src/test/resources/confPrivate.properties");
    }

    public static String getCommonProperty(String key) {
        return commonProperties.getProperty(key);
    }

    public static String getPrivateProperty(String key) {
        return privateProperties.getProperty(key);
    }

    public static boolean getCommonBoolProperty(String key) {
        return Boolean.parseBoolean(commonProperties.getProperty(key));
    }

    public static int getCommonIntProperty(String key) {
        return Integer.parseInt(commonProperties.getProperty(key));
    }

    public static int getPrivateIntProperty(String key) {
        return Integer.parseInt(commonProperties.getProperty(key));
    }
    public static boolean getPrivateBoolProperty(String key) {
        return Boolean.parseBoolean(privateProperties.getProperty(key));
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