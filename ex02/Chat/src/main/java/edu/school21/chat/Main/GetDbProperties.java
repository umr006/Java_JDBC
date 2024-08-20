package edu.school21.chat.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GetDbProperties {

    private static final Properties properties = new Properties();

    private GetDbProperties() {

    }

    static {
        loadProperties();
    }

    public static void loadProperties() {
        //mac
        //var is = new FileInputStream("/Users/new/Java_JDBC/ex00/Chat/src/main/resources/config.properties")
        try (var is = new FileInputStream("/home/umr006/Java_JDBC/ex01/Chat/src/main/resources/config.properties")) {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperties(String key) {
        return properties.getProperty(key);
    }
}
