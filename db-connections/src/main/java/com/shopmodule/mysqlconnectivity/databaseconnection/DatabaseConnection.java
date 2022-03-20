package com.shopmodule.mysqlconnectivity.databaseconnection;

import com.shopmodule.mysqlconnectivity.exception.ConnectionFailure;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * To get connection with the specified database by loading the driver.
 *
 * @author AswiniN
 */
public class DatabaseConnection {

    private static Connection connection = null;
    private static Properties properties = new Properties();

    /**
     * Get Connection of database
     *
     * @return
     */
    public static Connection getConnection() {

        try {
            properties.load(new FileInputStream("C:/karaf/etc/system.properties"));
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(properties.getProperty("karaf.lock.jdbc.url"), properties.getProperty("karaf.lock.jdbc.user"), properties.getProperty("karaf.lock.jdbc.password"));
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new ConnectionFailure("Connection failed!!");
        } catch (NullPointerException e){
            System.out.println("Connection might be null");
        }
        return connection;
    }
}










