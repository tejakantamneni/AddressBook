package com.jags;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by jparvathaneni on 12/20/15.
 */
public class AddressBookRunner {

    private static final Log LOG = LogFactory.getLog(AddressBookRunner.class);

    //private static final String addressFilePath = System.getProperty("java.io.tmpdir") + "address.dat";
    private static final String addressFilePath = FileUtils.getTempDirectoryPath() + "address.dat";
    private List<Address> addressList = new ArrayList<>();


    MenuHandler menuHandler = new MenuHandlerConsoleImpl();
    AddressHandler addressHandler = new AddressHandlerConsoleImpl();

    public void run(){
        try {
            LOG.debug("Loading addresses from saved file...");
            loadSavedAddress();
        } catch (Exception e) {
            LOG.error("Exception reading saved addresses ", e);
        }
        int choice = menuHandler.printMenuAndReadChoice();
        while(choice > 0 && choice < 6){

            switch (choice){
                case 1:
                    Address address = addressHandler.read();
                    addressList.add(address);
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
                    if(printMatchedAddress.isPresent()){
                        addressHandler.print(printMatchedAddress.get());
                        System.out.println("Enter the new address details:");
                        Address editAddress = addressHandler.readEdit(printMatchedAddress.get());
                        LOG.debug("editAddress = " + editAddress);
                        replaceEditTerm(editAddress,editUUID);
                    }else {
                        System.out.println("No address found to match with given UUID, please check again.");
                    }
                    break;
                case 4:
                    String addressDelete = addressHandler.readDeleteUUID();
                    deleteUUID(addressDelete);
                    break;
                case 5:
                    displayAllAddrs();
                    break;
                default:
                    break;
            }

            choice = menuHandler.printMenuAndReadChoice();
        }
    }
    private List<Address> findAddressMatchList(String searchString) {
        return addressList.stream().filter(addr -> (addr.matchName(searchString) || addr.matchEmail(searchString) || addr.matchPhone(searchString))).collect(Collectors.toList());
    }
    private Optional<Address> findAddressMatch(String UUIDToMatch) {
        return addressList.stream().filter( addr -> addr.matchUUID(UUIDToMatch)).findFirst();
    }
    private void deleteUUID(String UUIDToDelete){
        Optional<Address> address = addressList.stream().filter(addr -> addr.matchUUID(UUIDToDelete)).findFirst();

       if (address.isPresent()){
           addressList.remove(address.get());
        }else{
           System.err.println("No Address found with UUID to delete.");
       }
    }

    private void displayAllAddrs(){
        if(addressList.isEmpty()){
            System.out.println("No addresses in the list");
        }
        else{
            for(Address dis : addressList){
                System.out.println(dis);
            }
        }
    }

    private void replaceEditTerm(Address addrToReplace, String UUIDToEdit){
        int indexToEdit = 0;
        for(Address address : addressList){
            if(address.getUuid().equalsIgnoreCase(UUIDToEdit)){
                indexToEdit = addressList.indexOf(address);
                break;
            }
        }
        addressList.set(indexToEdit, addrToReplace);
    }


    private void loadSavedAddress() throws Exception{
        File addressFile = new File(addressFilePath);
        if(addressFile.exists()){
            FileInputStream fis = new FileInputStream(addressFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            addressList = (List<Address>) ois.readObject();
            LOG.info("found " + addressList.size() + " addresses in saved file.");
        }else{
            LOG.warn("No existing address file found, safe to ignore if this is a first run.");
        }
    }

    private void saveAddrsToFile(){
        try {
            LOG.debug("Saving address to file [" + addressFilePath + "]");
            FileOutputStream fileOutputStream = new FileOutputStream(addressFilePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(addressList);
            objectOutputStream.close();
            fileOutputStream.close();
            LOG.debug("address file saved successfully");
        }catch (IOException i){
            LOG.error("failed to save address file", i);
        }
    }

    public void attachShutDownHook(){
        LOG.debug("Attempting to attach a Shutdown hook");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                LOG.info("Saving the address to file on Shutdown...");
                saveAddrsToFile();
            }
        });
        LOG.debug("Shutdown hook attached successfully...");
    }

    public static void main(String[] args) {
        AddressBookRunner addressBookRunner = new AddressBookRunner();
        addressBookRunner.attachShutDownHook();
        addressBookRunner.run();

    }
}
