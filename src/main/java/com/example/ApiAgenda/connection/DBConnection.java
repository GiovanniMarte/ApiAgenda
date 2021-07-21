package com.example.ApiAgenda.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    public static Connection getConnection() {
        InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("config.properties");
        Properties props = new Properties();
        Connection connection = null;

        try {
            props.load(in);
            Class.forName(props.getProperty("DRIVER"));
            connection = DriverManager.getConnection(props.getProperty("URL"), props.getProperty("USER"), props.getProperty("PASS"));

        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
}