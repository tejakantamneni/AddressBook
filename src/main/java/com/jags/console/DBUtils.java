package com.jags.console;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;

/**
 * Created by tejakantamneni on 1/18/16.
 */
public class DBUtils {
    private static final Log LOG = LogFactory.getLog(DBUtils.class);

    public static final Connection createClosableConnection() {
        try {
            String dbURL = "jdbc:mysql://localhost:3306/address_book";
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(dbURL, "root", "");
        } catch (Exception except) {
            LOG.error("exception creating db connection", except);
        }
        return null;
    }

    public static void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.warn("Unable to close connection", e);
        }
    }

    public static void closeConnection(Statement stmt, Connection connection){
        try {
            stmt.close();
        } catch (SQLException e) {
            LOG.warn("Unable to close statement", e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.warn("Unable to close connection", e);
        }
    }

    public static void closeConnection(ResultSet resultSet, Statement stmt, Connection connection){
        try {
            resultSet.close();
        } catch (SQLException e) {
            LOG.warn("Unable to close result set", e);
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            LOG.warn("Unable to close statement", e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.warn("Unable to close connection", e);
        }
    }
}
