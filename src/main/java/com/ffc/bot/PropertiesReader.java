package main.java.com.ffc.bot;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private PropertiesReader() {

    }

    public static String getProperty(String propertyKey) {
        Properties properties = new Properties();

        try (InputStream inputStream = new FileInputStream("application.properties")) {
            properties.load(inputStream);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return properties.getProperty(propertyKey);
    }
}
