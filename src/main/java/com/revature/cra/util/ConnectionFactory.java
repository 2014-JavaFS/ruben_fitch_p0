package com.revature.cra.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static ConnectionFactory connectionFactory = new ConnectionFactory();

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
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
