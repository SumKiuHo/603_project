/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package academic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julia, Yannie
 */
/**
 * Represents a degree with a name, majors, minors, and an elective.
 */
public class Degree implements Serializable {
    private String chosenDegreeTitle; 
    private List<Major> majors; // List of majors
    private List<Minor> minors; // List of minors
    private Elective elective; // optional elective

    // constructor to initialize the degree name and lists
    public Degree(String chosenDegreeTitle) {
        this.chosenDegreeTitle = chosenDegreeTitle;
        this.majors = new ArrayList<>();
        this.minors = new ArrayList<>();
    }

    public String getName() {
        return chosenDegreeTitle;
    }

    public void setName(String chosenDegreeTitle) {
        this.chosenDegreeTitle = chosenDegreeTitle;
    }

    public List<Major> getMajors() {
        return majors;
    }

    public List<Minor> getMinors() {
        return minors;
    }

    public Elective getElective() {
        return elective;
    }

    public void setElective(Elective elective) {
        this.elective = elective;
    }

    // this adds a major to the degree, checks if the maximum number of majors is reached
    public void addMajor(Major primaryMajor) {
        if (majors.size() < 2) {
            majors.add(primaryMajor);
        } else {
            System.out.println("You can only have up to 2 majors.");
        }
    }

    // this adds a minor to the degree, checks if the maximum number of minors is reached
    public void addMinor(Minor initialMinor) {
        if (minors.size() < 2) {
            minors.add(initialMinor);
        } else {
            System.out.println("You can only have up to 2 minors.");
        }
    }

    
    public void printEnrolments() {
        System.out.println("Majors: ");
        for (Major major : majors) {
            System.out.println(" - " + major.getName());
        }
        System.out.println("Minors: ");
        for (Minor minor : minors) {
            System.out.println(" - " + minor.getName());
        }
        if (elective != null) {
            System.out.println("Elective: " + elective.getName());
        }
    }

    
    public void printGrades() {
        for (Major major : majors) {
            System.out.println("[Major: " + major.getName() + "]");
            major.printCourses();
        }
        for (Minor minor : minors) {
            System.out.println("[Minor: " + minor.getName() + "]");
            minor.printCourses();
        }
        if (elective != null) {
            System.out.println("[Elective: " + elective.getName() + "]");
            elective.printCourses();
        }
    }
}
