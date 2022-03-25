package com.shopmodule.authentication.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.shopmodule.mysqlconnectivity.databaseconnection.DatabaseConnection;
import com.shopmodule.exception.userdefinedexceptions.DatabaseAccessDeniedException;
import com.shopmodule.exception.userdefinedexceptions.InsertionFailedException;
import com.shopmodule.authentication.model.User;

/**
 * Retrieves data from table using SQL Queries to authenticate user.
 * 
 * @author AswiniN
 */
public class AuthenticatorDAO {

    /**
     * Gets data with the help of EmailId.
     * 
     * @param emailId
     */
    public User getUserEmailId(final String emailId) {
        final User user = new User();
        final String selectQuery = "SELECT email_id, password, type_of_user FROM user WHERE email_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {

            statement.setString(1, emailId);

            try (ResultSet userResultSet = statement.executeQuery()) {

                while (userResultSet.next()) {
                    user.setEmailId(userResultSet.getString(2));
                    user.setPassword(userResultSet.getString(3));
                    user.setUserType(userResultSet.getString(4));
                }
                return user;
            } 
        } catch (Exception e) {
            throw new DatabaseAccessDeniedException("Query may be invalid.");
        }    
    }

    /**
     * Inserts user .
     * 
     * @param user
     * @param typeOfUser
     */
    public boolean insertUserData(final User user, final String typeOfUser) {
        final String INSERT_ID_DETAILS = "INSERT INTO user (email_id, password, type_of_user)VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ID_DETAILS)) {

            preparedStatement.setString(1, user.getEmailId());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, typeOfUser);

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            throw new InsertionFailedException("Query may be invalid. Inserting user credentials failed!");
        }
    }
}
