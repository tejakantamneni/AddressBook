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
                        replaceEditTerm(editAddress, editUUID);
                    } else {
                        System.out.println("No address found to match with given UUID, please check again.");
                    }
                    break;
                case 4:
                    String addressDelete = addressHandler.readDeleteUUID();
                    deleteUUID(addressDelete);
                    break;
                case 5:
                    displayAllAddrs();
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
        //return addressList.stream().filter(addr -> (addr.matchName(searchString) || addr.matchEmail(searchString) || addr.matchPhone(searchString))).collect(Collectors.toList());
        return null;
    }

    private Optional<Address> findAddressMatch(String UUIDToMatch) {
        //return addressList.stream().filter(addr -> addr.matchUUID(UUIDToMatch)).findFirst();
        return null;
    }

    private void deleteUUID(String UUIDToDelete) {
/*
        Optional<Address> address = addressList.stream().filter(addr -> addr.matchUUID(UUIDToDelete)).findFirst();

        if (address.isPresent()) {
            addressList.remove(address.get());
        } else {
            System.err.println("No Address found with UUID to delete.");
        }
*/
    }

    private void displayAllAddrs() {
        try {
            List<Address> allAddressList = addressDAO.getAllAddressList(DBUtils.createClosableConnection());
            for(Address address : allAddressList){
                System.out.println(address);
            }
        } catch (SQLException e) {
            LOG.error("Error reading address information from database", e);
        }
    }

    private void replaceEditTerm(Address addrToReplace, String UUIDToEdit) {
       /* int indexToEdit = 0;
        for (Address address : addressList) {
            if (address.getUuid().equalsIgnoreCase(UUIDToEdit)) {
                indexToEdit = addressList.indexOf(address);
                break;
            }
        }
        addressList.set(indexToEdit, addrToReplace);*/
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
