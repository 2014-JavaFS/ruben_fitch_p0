package com.revature.cra.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static ConnectionFactory connectionFactory = new ConnectionFactory();
    private Properties properties = new Properties();

    private ConnectionFactory() {

    }

    static {
        try {
            Class.forName("org.postgres.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public Connection getConnection(){
        try {
            return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
