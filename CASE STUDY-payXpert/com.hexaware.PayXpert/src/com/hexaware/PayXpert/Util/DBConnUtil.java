package com.hexaware.PayXpert.Util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.hexaware.PayXpert.exceptions.DatabaseConnectionException;

public class DBConnUtil {

    private static Connection connection;

    public static Connection getConnection(String connectionString) throws DatabaseConnectionException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(connectionString);
                System.out.println("Database connection established.");
            } catch (SQLException e) {
                throw new DatabaseConnectionException(" Failed to connect to database: " + e.getMessage());
            }
        }
        return connection;
    }
}
