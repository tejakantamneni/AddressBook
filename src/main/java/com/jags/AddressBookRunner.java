package com.jags;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by jparvathaneni on 12/20/15.
 */
public class AddressBookRunner {

    private static final Log LOG = LogFactory.getLog(AddressBookRunner.class);

    private AddressDAO addressDAO;

    MenuHandler menuHandler = new MenuHandlerConsoleImpl();
    AddressHandler addressHandler = new AddressHandlerConsoleImpl();

    public AddressBookRunner() {
        this.addressDAO = new AddressDAOImpl();
        Connection connection = DBUtils.createClosableConnection();
        try {
            addressDAO.createTable(connection);
        } catch (SQLException e) {
            LOG.fatal("Unable to create table, ", e);
            System.exit(-1);
        }
        this.attachShutDownHook();
    }

    public AddressBookRunner(AddressDAO addressDAO, Connection connection) {
        this.addressDAO = new AddressDAOImpl();
        try {
            addressDAO.createTable(DBUtils.createClosableConnection());
        } catch (SQLException e) {
            LOG.fatal("Unable to create table, ", e);
            System.exit(-1);
        }
        this.attachShutDownHook();
    }

    public void run() {
        int choice = menuHandler.printMenuAndReadChoice();
        while (choice > 0 && choice < 6) {

            switch (choice) {
                case 1:
                    Address address = addressHandler.read();
                    try {
                        addressDAO.createAddress(DBUtils.createClosableConnection(), address);
                    } catch (SQLException e) {
                        LOG.error("Error inserting address into database: " + address, e);
                    }
                    break;
                case 2:
                    String searchString = addressHandler.readSearchTerm();
                    List<Address> matchedAddresses = findAddressMatchList(searchString);
                    addressHandler.print(matchedAddresses);
                    break;
                case 3:
                    String editString = addressHandler.readEditTerm();
                    List<Address> matchedAddressesToEdit = findAddressMatchList(editString);
                    addressHandler.print(matchedAddressesToEdit);
                    String editUUID = addressHandler.readUUIDToEdit();
                    Optional<Address> printMatchedAddress = findAddressMatch(editUUID);
                    if (printMatchedAddress.isPresent()) {
                        addressHandler.print(printMatchedAddress.get());
                        System.out.println("Enter the new address details:");
                        Address editAddress = addressHandler.readEdit(printMatchedAddress.get());
                        LOG.debug("editAddress = " + editAddress);
                        try {
                            addressDAO.replaceEditTerm(DBUtils.createClosableConnection(), editAddress, editUUID);
                        } catch (SQLException e) {
                            LOG.error("Unable to find address to match UUID: " + editUUID, e);
                        }
                    } else {
                        System.out.println("No address found to match with given UUID, please check again.");
                    }
                    break;
                case 4:
                    String addressDelete = addressHandler.readDeleteUUID();
                    try {
                        addressDAO.deleteAddress(DBUtils.createClosableConnection(), addressDelete );
                    } catch (SQLException e) {
                        LOG.error("Unable find the address with the UUID: " + addressDelete, e);
                    }
                    break;
                case 5:
                    try {
                        addressDAO.getAllAddressList(DBUtils.createClosableConnection());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
            choice = menuHandler.printMenuAndReadChoice();
        }
    }

    private List<Address> findAddressMatchList(String searchString) {
        try {
            return addressDAO.findMatchingAddressList(DBUtils.createClosableConnection(), searchString);
        } catch (Exception e) {
            LOG.error("Unable to search in the database with given search string: " + searchString, e);
        }
        return null;
    }

    private Optional<Address> findAddressMatch(String UUIDToMatch) {
        try {
            return addressDAO.findMatchingAddress(DBUtils.createClosableConnection(), UUIDToMatch);
        } catch (SQLException e) {
            LOG.error("Unable to find an address with the given UUID: " + UUIDToMatch, e);
        }
        return null;
    }

     public void attachShutDownHook() {
        LOG.debug("Attempting to attach a Shutdown hook");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                //Any cleanup stuff...
            }
        });
        LOG.debug("Shutdown hook attached successfully...");
    }

    public static void main(String[] args) throws SQLException {
        AddressBookRunner addressBookRunner = new AddressBookRunner();
        addressBookRunner.run();

    }
}
