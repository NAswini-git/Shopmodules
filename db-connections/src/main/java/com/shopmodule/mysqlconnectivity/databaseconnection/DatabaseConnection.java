package com.shopmodule.mysqlconnectivity.databaseconnection;

import com.shopmodule.mysqlconnectivity.exception.ConnectionFailure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Map;

/**
 * To get connection with the specified database by loading the driver.
 *
 * @author AswiniN
 */
public class DatabaseConnection {

    private static Connection connection = null;
    private static Map<String, String> properties;
    private static DatabaseConnection databaseConnection;

    private DatabaseConnection() {
    }

    /**
     * Creates instance for this class.
     * @return
     */
    public static DatabaseConnection getInstance() {
        if (databaseConnection == null){
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }

    /**
     * Sets property details.
     * @param properties
     */

    public void setProperty(Map<String, String> properties) {
        this.properties = properties;
    }

    /**
     * Get Connection of database
     *
     * @return
     */
    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(properties.get("jdbc.url"), properties.get("jdbc.user"), properties.get("jdbc.password"));
        } catch (SQLException|ClassNotFoundException e) {
            throw new ConnectionFailure("Connection Failed!");
        } catch (NullPointerException e) {
            System.out.println("Connection might be null");
        }
        return connection;
    }
}










