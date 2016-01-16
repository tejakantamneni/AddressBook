package com.jags;

import java.util.Scanner;

/**
 * Created by jparvathaneni on 12/20/15.
 */
public class MenuHandlerConsoleImpl implements MenuHandler {
    @Override
    public int printMenuAndReadChoice() {
        System.out.println("*************c*********************************** ");
        System.out.println("*********** 1. Add New Address ****************** ");
        System.out.println("*********** 2. Search Address ******************* ");
        System.out.println("*********** 3. Edit Address ********************* ");
        System.out.println("*********** 4. Delete Address ******************* ");
        System.out.println("*********** 5. Display All Addresses ************ ");
        System.out.println("*********** 6. Write all addresses to a file **** ");
        System.out.println("*********** 7. Exit ***************************** ");
        System.out.println("*************c*********************************** ");
        System.out.println("");
        System.out.println("Enter your choice: ");
        Scanner scanner = new Scanner(System.in) ;
        return scanner.nextInt();
    }
}
