/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package student;

import academic.Degree;
import studentWallet.Wallet;
import java.io.Serializable;

/**
 *
 * @author julia
 */

/**
 * Represents a student with personal information, degree details, and wallet.
 */

public class Student implements Serializable {
    private String fullName; 
    private String dateOfBirth; 
    private String email; 
    private String studentId; 
    private String password; 
    private Degree degree; 
    private Wallet wallet; 

    public Student(String fullName, String dateOfBirth, String email, String studentId, String password) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.studentId = studentId;
        this.password = password; 
        this.degree = new Degree("");
        this.wallet = new Wallet();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getPassword() {
        return password; // New getter for password
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Degree getDegree() {
        return degree;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public boolean hasEnrolments() {
        return !degree.getMajors().isEmpty() || !degree.getMinors().isEmpty() || degree.getElective() != null;
    }

    public void printEnrolments() {
        System.out.println("Degree: " + degree.getName());
        degree.printEnrolments();
    }

    public void printGrades() {
        degree.printGrades();
    }
}
