package org.FluffyTerror.managers;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

    private final Properties properties = new Properties();//Переменна для хранения данных считанных из файла properties и переданных пользователем
    // Т.е.переменная для хранения пользовательских properties

    private static PropertyManager INSTANCE = null;

    private PropertyManager() {
        loadApplicationProperties();
    }

    public static PropertyManager getPropertyManager() {
        if (INSTANCE == null) {
            INSTANCE = new PropertyManager();
        }
        return INSTANCE;
    }

    private void loadApplicationProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(
                System.getProperty("propFile", "application") + ".properties")) {
            if (input == null) {
                throw new FileNotFoundException("Property file not found in resources");
            }
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
