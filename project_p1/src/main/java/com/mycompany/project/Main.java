/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.project;

import java.util.Scanner;

/**
 *
 * @author julia, Yannie
 */

/**
 * Entry point for the AUT Management System application.
 */
public class Main {
    
    private static final Scanner scanner = new Scanner(System.in);
    private static final Account account = new Account();
    
    public static void main(String[] args) {
        System.out.println("\nHello and welcome to the AUT Management System!");
        
        while (true) {
            System.out.println("\nWhat would you like to do today?\n1. Create a new student account\n2. Access an existing account\n3. Exit the system\n");
            String option = scanner.nextLine();
            boolean validInput = false;
            
            switch (option) {
                case "1":
                    account.initiateStudentAccountCreation();
                    validInput = true;
                    break;
                case "2":
                    account.authenticateExistingStudent();
                    validInput = true;
                    break;
                case "3":
                    System.out.println("Thank you for using the AUT Management System! Have a lovely day :D");
                    System.exit(0);
                default:
                    System.out.println("Invalid option! Please try again.");
            }  
            
            if (validInput) {
                account.navigateToStudentDashboard();
            }
        }
    }
}
