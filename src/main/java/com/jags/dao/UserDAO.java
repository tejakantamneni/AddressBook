package com.jags.dao;

import com.jags.model.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by JParvathaneni on 1/24/16.
 */
public interface UserDAO {

    User authenticate(Connection connection, String username, String password) throws SQLException;
}
