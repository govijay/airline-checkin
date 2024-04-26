package com.airline.checkin.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionProvider {

    public static Connection getConnection() {
        Properties properties = new Properties();
        try {
            properties.load(ConnectionProvider.class.getClassLoader().getResourceAsStream("application.properties"));
            Class.forName(properties.getProperty("mysql.connection.driver"));
            String url = properties.getProperty("mysql.connection.url");
            String user = properties.getProperty("mysql.connection.username");
            String password = properties.getProperty("mysql.connection.password");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
