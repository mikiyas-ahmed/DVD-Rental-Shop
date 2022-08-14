package services;

import DatabaseConnectivity.DatabaseConnector;

import java.sql.SQLException;

public class DatabaseConnectionManager {
    public static void connect()  {
        DatabaseConnector connection= new DatabaseConnector();
        try {
            connection.openDatabaseConnection();
            connection.createAllTablesIfNotExist();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.closeDatabaseConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
