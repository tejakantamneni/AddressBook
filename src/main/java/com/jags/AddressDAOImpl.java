package com.jags;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;

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
    public void insertData(Connection dbCon, Address address) throws SQLException {

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
    public void readData(Connection dbCon) throws SQLException {
        String sql = "select UUID, FIRSTNAME, LASTNAME, ADDRESS1, ADDRESS2, CITY, STATE, ZIP, PHONE, EMAIL from address";
        Statement stmt = dbCon.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
        while (resultSet.next()) {
            String uuid = resultSet.getString("UUID");
            String firstname = resultSet.getString("FIRSTNAME");
            LOG.debug(uuid + "  - " + firstname);
        }
        DBUtils.closeConnection(resultSet, stmt, dbCon);
    }

}
