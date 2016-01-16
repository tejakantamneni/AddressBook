package com.jags;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by jparvathaneni on 12/20/15.
 */
public class AddressBookRunner {

    private List<Address> addressList = new ArrayList<>();

    MenuHandler menuHandler = new MenuHandlerConsoleImpl();
    AddressHandler addressHandler = new AddressHandlerConsoleImpl();

    public void run(){
        int choice = menuHandler.printMenuAndReadChoice();
        while(choice > 0 && choice <= 6){

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
                        System.out.println("editAddress = " + editAddress);
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
                case 6:
                    System.out.println("first step in writing");
                    Address saveAddress = new Address();
                    System.out.println("came into case 6");
                    saveAddrsToFile(saveAddress);
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

    private void saveAddrsToFile(Address saveAddr){
        try {
            String fileName = "address.dat";
            FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("java.io.tmpdir") + fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(saveAddr);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("File written and closed");
        }catch (IOException i){
            i.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.io.tmpdir"));
        AddressBookRunner addressBookRunner = new AddressBookRunner();
        addressBookRunner.run();

    }
}
