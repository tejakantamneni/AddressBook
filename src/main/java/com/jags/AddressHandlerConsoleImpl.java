package com.jags;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Created by jparvathaneni on 12/20/15.
 */
public class AddressHandlerConsoleImpl implements AddressHandler {
    @Override
    public Address read() {
        Scanner scanner = new Scanner(System.in);

        Address address = new Address();
        address.setUuid(UUID.randomUUID().toString());

        System.out.println("Enter First Name: ");
        address.setFirstName(scanner.nextLine());

        System.out.println("Enter Last Name: ");
        address.setLastName(scanner.nextLine());

        System.out.println("Enter Address Line 1: ");
        address.setLine1(scanner.nextLine());

        System.out.println("Enter Address Line 2: ");
        address.setLine2(scanner.nextLine());

        System.out.println("Enter City: ");
        address.setCity(scanner.nextLine());

        System.out.println("Enter State: ");
        address.setState(scanner.nextLine());

        System.out.println("Enter Zip: ");
        address.setZip(scanner.nextLine());

        System.out.println("Enter Phone: ");
        address.setPhoneNumber(scanner.nextLine());

        System.out.println("Enter Email: ");
        address.setEmail(scanner.nextLine());

        return address;
    }

    @Override
    public String readSearchTerm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter search term:");
        return scanner.nextLine();
    }

    @Override
    public String readDeleteUUID() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter UUID to delete:");
        return scanner.nextLine();
    }

    @Override
    public String readEditTerm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name, email or phone for the record to be edited:");
        return scanner.nextLine();
    }

    @Override
    public String readUUIDToEdit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter UUID to edit:");
        return scanner.nextLine();
    }

    @Override
    public void print(Address address) {
        System.out.println(address);

    }

    @Override
    public void print(List<Address> addressList) {
        if(addressList != null) {
            for(Address addr: addressList){
                print(addr);
            }
        }
    }

    private String getExistingOrNew(Scanner scanner, String fieldDesc, String fieldValue){
        System.out.println("Enter new " + fieldDesc +" (Leave blank if no change), current value: " + fieldValue);
        String inputValue = scanner.nextLine();
        if(inputValue != null && inputValue.trim().length() > 0){
            return inputValue;
        }
        return fieldValue;
    }

    @Override
    public Address readEdit(Address address) {
        Scanner scanner = new Scanner(System.in);

        address.setFirstName(getExistingOrNew(scanner, "First Name", address.getFirstName()));
        address.setLastName(getExistingOrNew(scanner, "Last Name", address.getLastName()));
        address.setLine1(getExistingOrNew(scanner, "Address Line 1", address.getLine1()));
        address.setLine2(getExistingOrNew(scanner, "Address Line 2", address.getLine2()));
        address.setCity(getExistingOrNew(scanner, "City", address.getCity()));
        address.setState(getExistingOrNew(scanner, "State", address.getState()));
        address.setZip(getExistingOrNew(scanner, "Zip", address.getZip()));
        address.setPhoneNumber(getExistingOrNew(scanner, "Phone", address.getPhoneNumber()));
        address.setEmail(getExistingOrNew(scanner, "email", address.getEmail()));

        return address;
    }

}
