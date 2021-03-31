package common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/mpp";
    private static Connection connection = null;

    protected static void connectToDatabase() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(CONNECTION_STRING,"root","");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection Failed: " + e);
            e.printStackTrace();
        }
    }

    public static void closeDatabaseConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());

        }
    }

    public static Connection getDatabaseConnection() throws SQLException {
        if (connection == null)
            connectToDatabase();
        return connection;
    }
}

