package com.jags;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by JParvathaneni on 1/18/16.
 */
public interface AddressDAO {
    void createTable(Connection connection) throws SQLException;

    void createAddress(Connection connection, Address address) throws SQLException;

    List<Address> getAllAddressList(Connection connection) throws SQLException;

    List<Address> findMatcingAddressList(Connection connection, String searchTerm) throws SQLException;

    Optional<Address> findMatchingAddress(Connection connection, String UUIDToMatch) throws SQLException;

}
