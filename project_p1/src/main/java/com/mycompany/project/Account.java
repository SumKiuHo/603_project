/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.project;

import java.util.*;
import academic.Course;
import academic.Elective;
import academic.Major;
import academic.Minor;
import student.Student;

/**
 *
 * @author User
 */

/**
 * Handles account creation, access, and student management functionality.
 */
public class Account {
    private final Scanner scanner;
    private static Student currentStudent;

    public Account() {
        this.scanner = new Scanner(System.in);
    }

    // this initiates the process to create a new student account
    public final void initiateStudentAccountCreation() {
        System.out.println("Looks like we don't have an account for you yet. Let's create one!\nFull Name: ");
        String studentFullName = scanner.nextLine();

        System.out.print("Date of Birth (dd-MM-yyyy): ");
        String dateOfBirthInput = scanner.nextLine();

        System.out.print("Email Address: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String userAccountPassword = scanner.nextLine();

        String studentId = createUniqueStudentIdentifier();

        currentStudent = new Student(studentFullName, dateOfBirthInput, email, studentId, userAccountPassword);

        System.out.println("Please input your Bachelor's Degree: ");
        String chosenDegreeTitle = scanner.nextLine();
        currentStudent.getDegree().setName(chosenDegreeTitle);

        System.out.println("\nWhich one are you undertaking: \n"
                + "1. Double Major\n"
                + "2. One Major & Double Minor\n"
                + "3. One Major & One Minor & One Elective\n"
                + "Press enter to skip if you don't have one yet"
        );
        String degreeOption = scanner.nextLine();

        // for handling enrolment based on the user's input
        processDegreeEnrolmentOptions(degreeOption);

        // Call save data method
        ManageStudentData.persistStudentDataToFile(currentStudent);
        System.out.println("Congratulations, " + studentFullName + "! Your account has been successfully created. You can now log in and explore your options.");
        navigateToStudentDashboard();
    }

    // to authenticate an existing student by name and password
    public final void authenticateExistingStudent() {
        System.out.print("Please enter your full name to log in: ");
        String name = scanner.nextLine();
        System.out.print("Please enter your password: ");
        String userAccountPassword = scanner.nextLine();

        Student loadedStudent = ManageStudentData.loadStudentData(name);
        if (loadedStudent != null && loadedStudent.getPassword().equals(userAccountPassword)) {
            currentStudent = loadedStudent;
            System.out.println("\nWelcome back, " + name + "!");
            navigateToStudentDashboard();
        } else {
            System.out.println("Account not found or incorrect password.");
        }
    }

    // to navigate to the student's dashboard after login
    public final void navigateToStudentDashboard() {
        while (true) {
            System.out.print("\nYour Home page: \n"
                    + "1. Check your personal information\n"
                    + "2. View your enrolments\n"
                    + "3. View your grades and required grades to pass\n"
                    + "4. Manage your wallet\n"
                    + "5. Exit the system\n"
            );
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    reviewAndUpdatePersonalDetails();
                    break;
                case "2":
                    displayEnrolmentDetails();
                    break;
                case "3":
                    showStudentGradeReport();
                    break;
                case "4":
                    handleStudentWalletOperations();
                    break;
                case "5":
                    System.out.println("Thank you for using the AUT Management System! Have a lovely day :D");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    // reviews and updates personal details
    public final void reviewAndUpdatePersonalDetails() {
        System.out.println("\nPersonal Information:" 
                + "\nFull Name: " + currentStudent.getFullName()
                + "\nDate of Birth: " + currentStudent.getDateOfBirth()
                + "\nEmail Address: " + currentStudent.getEmail()
                + "\nStudent ID: " + currentStudent.getStudentId()
        );
        
        System.out.println("\nWould you like to update your personal information?\n"
                + "Press 1 to continue or press 2 to return: \n"
        );
        int input = scanner.nextInt();
        scanner.nextLine();

        if (input == 1) {
            System.out.println("Please enter your full name: ");
            String studentFullName = scanner.nextLine();
            currentStudent.setFullName(studentFullName);

            System.out.print("Date of Birth (dd-MM-yyyy): ");
            String dateOfBirthInput = scanner.nextLine();
            currentStudent.setDateOfBirth(dateOfBirthInput);

            System.out.print("Email Address: ");
            String email = scanner.nextLine();
            currentStudent.setEmail(email);

            System.out.print("Password: ");
            String userAccountPassword = scanner.nextLine();
            currentStudent.setPassword(userAccountPassword);

            System.out.println("Personal information has been saved.");
        } else {
            System.out.println("Returning to the Home Page.\n");
        }
        ManageStudentData.persistStudentDataToFile(currentStudent);
    }

    // to display enrolment details or prompts for enrolment input
    public final void displayEnrolmentDetails() {
        if (currentStudent.hasEnrolments()) {
            currentStudent.printEnrolments();
            System.out.println("\nWould you like to update your Enrolment? \n"
                    + "Press 1 to continue or press 2 to return: \n");
            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 1) {
                System.out.println("Please provide your updated enrolment details:");
                processDegreeEnrolmentOptions(""); // Call with empty input to allow user to choose again
            } else {
                System.out.println("Returning to the Home Page.\n");
            }
        } else {
            System.out.println("No enrolment information available.");
            processDegreeEnrolmentOptions(""); // Prompt for enrolment details
        }
    }

    // processes degree enrolment options based on user input
    private void processDegreeEnrolmentOptions(String degreeOption) {
        switch (degreeOption) {
            case "1":
                System.out.print("Please enter your 1st Major: ");
                String primaryMajor = scanner.nextLine();
                currentStudent.getDegree().addMajor(new Major(primaryMajor));

                System.out.print("Please enter the Course for your 1st Major: ");
                String primaryMajorCourseName = scanner.nextLine();
                currentStudent.getDegree().getMajors().get(0).addCourse(new Course(primaryMajorCourseName));

                System.out.print("Please enter your 2nd Major: ");
                String secondaryMajor = scanner.nextLine();
                currentStudent.getDegree().addMajor(new Major(secondaryMajor));

                System.out.print("Please enter the Course for your 2nd Major: ");
                String secondaryMajorCourseName = scanner.nextLine();
                currentStudent.getDegree().getMajors().get(1).addCourse(new Course(secondaryMajorCourseName));
                break;

            case "2":
                System.out.print("Please enter your Major: ");
                primaryMajor = scanner.nextLine();
                currentStudent.getDegree().addMajor(new Major(primaryMajor));

                System.out.print("Please enter the Course for your Major: ");
                primaryMajorCourseName = scanner.nextLine();
                currentStudent.getDegree().getMajors().get(0).addCourse(new Course(primaryMajorCourseName));

                System.out.print("Please enter your 1st Minor: ");
                String initialMinor = scanner.nextLine();
                currentStudent.getDegree().addMinor(new Minor(initialMinor));

                System.out.print("Please enter the Course for your 1st Minor: ");
                String initialMinorCourseName = scanner.nextLine();
                currentStudent.getDegree().getMinors().get(0).addCourse(new Course(initialMinorCourseName));

                System.out.print("Please enter your 2nd Minor: ");
                String secondaryMinor = scanner.nextLine();
                currentStudent.getDegree().addMinor(new Minor(secondaryMinor));

                System.out.print("Please enter the Course for your 2nd Minor: ");
                String secondaryMinorCourseName = scanner.nextLine();
                currentStudent.getDegree().getMinors().get(1).addCourse(new Course(secondaryMinorCourseName));
                break;

            case "3":
                System.out.print("Please enter your Major: ");
                primaryMajor = scanner.nextLine();
                currentStudent.getDegree().addMajor(new Major(primaryMajor));

                System.out.print("Please enter the Course for your Major: ");
                primaryMajorCourseName = scanner.nextLine();
                currentStudent.getDegree().getMajors().get(0).addCourse(new Course(primaryMajorCourseName));

                System.out.print("Please enter your Minor: ");
                initialMinor = scanner.nextLine();
                currentStudent.getDegree().addMinor(new Minor(initialMinor));

                System.out.print("Please enter the Course for your Minor: ");
                String initialMinorCourse = scanner.nextLine();
                currentStudent.getDegree().getMinors().get(0).addCourse(new Course(initialMinorCourse));

                System.out.print("Please enter your Elective: ");
                String electiveName = scanner.nextLine();
                currentStudent.getDegree().setElective(new Elective(electiveName));

                System.out.print("Please enter the Course for your Elective: ");
                String electiveCourseName = scanner.nextLine();
                currentStudent.getDegree().getElective().addCourse(new Course(electiveCourseName));
                break;

            default:
                System.out.println("No enrolment data entered.");
                break;
        }
    }

    // displays the student's grades
    public final void showStudentGradeReport() {
        System.out.println("Let's check if you meet all the academic requirements to pass!\n"
                + "Here are your current grades for " + currentStudent.getDegree().getName() + ": ");
        currentStudent.printGrades();
    }

    // this manages student wallet operations
    public final void handleStudentWalletOperations() {
        while (true) {
            System.out.println("\nAccessing your student wallet..."
                    + "\nGeneral Use Balance (for cafes, vending machines): $" + currentStudent.getWallet().getGeneralBalance()
                    + "\nPrinting Credits: $" + currentStudent.getWallet().getPrintingBalance()
                    + "\nNote: General balance and Printing credits are separate accounts.\n"
            );

            System.out.println("\nWhat would you like to do? \n"
                    + "1. Top up your general use balance\n"
                    + "2. Top up your printing credits\n"
                    + "3. View transaction history\n"
                    + "4. Return to Home page\n");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.print("Enter the amount to add to your general use balance: ");
                    double generalTopUp = Double.parseDouble(scanner.nextLine());
                    currentStudent.getWallet().topUpGeneralBalance(generalTopUp);
                    System.out.println("Your new general use balance is: $" + String.format("%.2f", currentStudent.getWallet().getGeneralBalance()));
                    break;
                case "2":
                    System.out.print("Enter the amount to add to your printing credits: ");
                    double printingTopUp = Double.parseDouble(scanner.nextLine());
                    currentStudent.getWallet().topUpPrintingBalance(printingTopUp);
                    System.out.println("Your new printing credits balance is: $" + String.format("%.2f", currentStudent.getWallet().getPrintingBalance()));
                    break;
                case "3":
                    System.out.println("Here are your recent transactions:");
                    for (String transaction : currentStudent.getWallet().getTransactionHistory()) {
                        System.out.println(transaction);
                    }
                    break;
                case "4":
                    System.out.println("Returning to the main menu...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // this generates a unique student ID
    private static String createUniqueStudentIdentifier() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            id.append(letters.charAt(random.nextInt(letters.length())));
        }
        for (int i = 0; i < 3; i++) {
            id.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return id.toString();
    }
}
