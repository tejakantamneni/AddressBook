package com.jags;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by JParvathaneni on 1/18/16.
 */
public class AddressDAOImpl implements AddressDAO {

    private static final Log LOG = LogFactory.getLog(AddressHandlerConsoleImpl.class);

    @Override
    public void createTable(Connection dbCon) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS ADDRESS" +
                "(" +
                "UUID varchar(255)," +
                "FirstName varchar(255)," +
                "LastName varchar(255)," +
                "Address1 varchar(255)," +
                "Address2 varchar(255)," +
                "City varchar(255)," +
                "State varchar(255)," +
                "Zip varchar(255)," +
                "Phone varchar(255)," +
                "eMail varchar(255)" +
                ")";

        Statement statement = dbCon.createStatement();
        statement.execute(sql);

        DBUtils.closeConnection(statement, dbCon);
    }

    @Override
    public void createAddress(Connection dbCon, Address address) throws SQLException {

        String pSQL = "INSERT INTO ADDRESS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt1 = dbCon.prepareStatement(pSQL);
        stmt1.setString(1, address.getUuid());
        stmt1.setString(2, address.getFirstName());
        stmt1.setString(3, address.getLastName());
        stmt1.setString(4, address.getLine1());
        stmt1.setString(5, address.getLine2());
        stmt1.setString(6, address.getCity());
        stmt1.setString(7, address.getState());
        stmt1.setString(8, address.getZip());
        stmt1.setString(9, address.getPhoneNumber());
        stmt1.setString(10, address.getEmail());

        stmt1.execute();

        System.out.println("************ Addressed saved to file ************");

        DBUtils.closeConnection(stmt1, dbCon);
    }

    @Override
    public void replaceEditTerm(Connection connection, Address addrToReplace, String UUIDToEdit) throws SQLException {
        //Address address = new Address();
        String sql = "UPDATE Address SET " +
                " FIRSTNAME = ? ," +
                " LASTNAME = ? ," +
                " ADDRESS1 = ? ," +
                " ADDRESS2 = ? ," +
                " CITY = ? ," +
                " STATE = ? ," +
                " ZIP = ? ," +
                " PHONE = ? ," +
                " EMAIL = ? " +
                " WHERE UUID = ? ";

        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, addrToReplace.getFirstName());
        stmt.setString(2, addrToReplace.getLastName());
        stmt.setString(3, addrToReplace.getLine1());
        stmt.setString(4, addrToReplace.getLine2());
        stmt.setString(5, addrToReplace.getCity());
        stmt.setString(6, addrToReplace.getState());
        stmt.setString(7, addrToReplace.getZip());
        stmt.setString(8, addrToReplace.getPhoneNumber());
        stmt.setString(9, addrToReplace.getEmail());
        stmt.setString(10, UUIDToEdit);

        stmt.execute();

        System.out.println("Address updated with new values");

        DBUtils.closeConnection(stmt, connection);

    }

    @Override
    public Optional<Address> findMatchingAddress(Connection connection, String UUIDToMatch) throws SQLException {
        List<Address> addressList = new ArrayList<>();
        String query = "select * from address " +
                " where UUID = ? ";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, UUIDToMatch);

        ResultSet resultSet = stmt.executeQuery();
        parseResultSet(addressList, resultSet);
        DBUtils.closeConnection(resultSet, stmt, connection);

        Optional<Address> address = addressList.stream().findFirst();

        return address;

    }

    @Override
    public List<Address> getAllAddressList(Connection dbCon) throws SQLException {
        List<Address> addressList = new ArrayList<>();
        String sql = "select UUID, FIRSTNAME, LASTNAME, ADDRESS1, ADDRESS2, CITY, STATE, ZIP, PHONE, EMAIL from address";
        Statement stmt = dbCon.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
        parseResultSet(addressList, resultSet);
        DBUtils.closeConnection(resultSet, stmt, dbCon);

        return addressList;
    }

    @Override
    public List<Address> findMatcingAddressList(Connection connection, String searchTerm) throws SQLException {
        List<Address> addressList = new ArrayList<>();
        String query = "select * from address " +
                " where firstname like ? " +
                " or lastname like ? ";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, searchTerm + "%");
        stmt.setString(2, searchTerm + "%");

        ResultSet resultSet = stmt.executeQuery();
        parseResultSet(addressList, resultSet);
        DBUtils.closeConnection(resultSet, stmt, connection);

        return addressList;
    }

    private void parseResultSet(List<Address> addressList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Address address = new Address();
            address.setUuid(resultSet.getString("UUID"));
            address.setFirstName(resultSet.getString("FIRSTNAME"));
            address.setLastName(resultSet.getString("LASTNAME"));
            address.setLine1(resultSet.getString("ADDRESS1"));
            address.setLine2(resultSet.getString("ADDRESS2"));
            address.setCity(resultSet.getString("CITY"));
            address.setState(resultSet.getString("STATE"));
            address.setZip(resultSet.getString("ZIP"));
            address.setPhoneNumber(resultSet.getString("PHONE"));
            address.setEmail(resultSet.getString("EMAIL"));
            addressList.add(address);
        }
    }

}
