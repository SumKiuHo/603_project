package com.mycompany.project;

import java.io.*;
import academic.Course;
import academic.Elective;
import academic.Major;
import academic.Minor;
import student.Student;

/**
 *
 * @author Yannie, julia
 * 
 * Using BufferedWriter to save the data, and BufferedReader to get the data
 */

/**
 * Handles saving and loading student data using file I/O operations.
 */

public class ManageStudentData {

    // this saves student data to a text file
    public static void persistStudentDataToFile(Student student) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(student.getFullName() + ".txt"))) {
            bw.write(student.getFullName() + "\n");
            bw.write(student.getDateOfBirth() + "\n");
            bw.write(student.getEmail() + "\n");
            bw.write(student.getStudentId() + "\n");
            bw.write(student.getPassword() + "\n"); // Save password
            bw.write(student.getDegree().getName() + "\n");

            for (Major major : student.getDegree().getMajors()) {
                bw.write("Major:" + major.getName() + "\n");
                for (Course course : major.getCourses()) {
                    bw.write("Course:" + course.getCourseName() + "\n");
                }
            }

            for (Minor minor : student.getDegree().getMinors()) {
                bw.write("Minor:" + minor.getName() + "\n");
                for (Course course : minor.getCourses()) {
                    bw.write("Course:" + course.getCourseName() + "\n");
                }
            }

            if (student.getDegree().getElective() != null) {
                bw.write("Elective:" + student.getDegree().getElective().getName() + "\n");
                for (Course course : student.getDegree().getElective().getCourses()) {
                    bw.write("Course:" + course.getCourseName() + "\n");
                }
            }

            // Save Wallet info in a consistent format
            bw.write("Wallet General Balance:" + String.format("%.2f", student.getWallet().getGeneralBalance()) + "\n");
            bw.write("Wallet Printing Balance:" + String.format("%.2f", student.getWallet().getPrintingBalance()) + "\n");
            for (String transaction : student.getWallet().getTransactionHistory()) {
                bw.write("Transaction:" + transaction + "\n");
            }

        } catch (IOException e) {
            System.out.println("Error saving student data.");
        }
    }

    // this loads student data from a text file
    public static Student loadStudentData(String name) {
        try (BufferedReader br = new BufferedReader(new FileReader(name + ".txt"))) {
            String fullName = br.readLine();
            String dob = br.readLine();
            String email = br.readLine();
            String studentId = br.readLine();
            String password = br.readLine(); // Load password
            String degreeName = br.readLine();

            Student student = new Student(fullName, dob, email, studentId, password); // pass password to constructor
            student.getDegree().setName(degreeName);

            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Major:")) {
                    Major major = new Major(line.substring(6));
                    student.getDegree().addMajor(major);

                    while ((line = br.readLine()) != null && line.startsWith("Course:")) {
                        major.addCourse(new Course(line.substring(7)));
                    }

                } else if (line.startsWith("Minor:")) {
                    Minor minor = new Minor(line.substring(6));
                    student.getDegree().addMinor(minor);

                    while ((line = br.readLine()) != null && line.startsWith("Course:")) {
                        minor.addCourse(new Course(line.substring(7)));
                    }

                } else if (line.startsWith("Elective:")) {
                    Elective elective = new Elective(line.substring(9));
                    student.getDegree().setElective(elective);
                    while ((line = br.readLine()) != null && line.startsWith("Course:")) {
                        elective.addCourse(new Course(line.substring(7)));
                    }
                } else if (line.startsWith("Wallet General Balance:")) {
                    try {
                        double generalBalance = Double.parseDouble(line.substring(22).trim());
                        student.getWallet().topUpGeneralBalance(generalBalance);
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing general balance. Defaulting to 0.0");
                        student.getWallet().topUpGeneralBalance(0.0);
                    }
                } else if (line.startsWith("Wallet Printing Balance:")) {
                    try {
                        double printingBalance = Double.parseDouble(line.substring(23).trim());
                        student.getWallet().topUpPrintingBalance(printingBalance);
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing printing balance. Defaulting to 0.0");
                        student.getWallet().topUpPrintingBalance(0.0);
                    }
                } else if (line.startsWith("Transaction:")) {
                    student.getWallet().getTransactionHistory().add(line.substring(12));
                }
            }

            return student;
        } catch (IOException e) {
            System.out.println("Error loading student data.");
            return null;
        }
    }
}
