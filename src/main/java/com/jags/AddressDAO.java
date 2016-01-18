package com.jags;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by JParvathaneni on 1/18/16.
 */
public interface AddressDAO {
    void createTable(Connection connection) throws SQLException;

    void createAddress(Connection connection, Address address) throws SQLException;

    List<Address> getAllAddressList(Connection connection) throws SQLException;

}
