package com.jags;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by JParvathaneni on 1/18/16.
 */
public interface AddressDAO {

    public Connection createConnection();

    public void createTable(Connection connection) throws SQLException;
    public void insertData(Connection connection, Address address) throws SQLException;
    public void readData(Connection connection) throws SQLException;

}
