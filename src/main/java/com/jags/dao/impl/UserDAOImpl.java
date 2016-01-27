package com.jags.dao.impl;

import com.jags.console.DBUtils;
import com.jags.dao.UserDAO;
import com.jags.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by JParvathaneni on 1/24/16.
 */
public class UserDAOImpl implements UserDAO {
    @Override
    public User authenticate(Connection connection, String username, String password) throws SQLException {
        String QUERY = "SELECT FIRST_NAME, LAST_NAME, user_id FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        while (resultSet.next()) {
            user = new User();
            user.setFirstName(resultSet.getString("FIRST_NAME"));
            user.setLastName(resultSet.getString("LAST_NAME"));
            user.setUserID(resultSet.getInt("USER_ID"));
            user.setUsername(username);
            user.setPassword(password);
        }
        DBUtils.closeConnection(resultSet, preparedStatement, connection);
        return user;
    }
}
