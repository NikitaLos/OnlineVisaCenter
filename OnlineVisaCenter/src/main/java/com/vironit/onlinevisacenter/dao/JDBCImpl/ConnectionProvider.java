package com.vironit.onlinevisacenter.dao.JDBCImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private static Connection connection;

    private ConnectionProvider() {
    }

    public static synchronized Connection getConnection() {
        if (connection == null){
            connection = makeConnection();
        }
        return connection;

    }

    private static Connection makeConnection(){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/visa_center", "postgres",
                    "21032018lna");
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Exception");
        }
        return connection;
    }
}
