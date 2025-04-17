package com.hexaware.PayXpert.Util;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    public static String getConnectionString(String fileName) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(fileName)) {
            props.load(fis);
        } catch (IOException e) {
            System.err.println(" Error reading DB properties: " + e.getMessage());
            return null;
        }

      
        String host = props.getProperty("db.host");
        String port = props.getProperty("db.port");
        String dbName = props.getProperty("db.name");
        String user = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        
        return String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s", host, port, dbName, user, password);
    }
}
