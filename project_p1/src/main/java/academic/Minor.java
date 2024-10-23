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
 * Represents a minor with a list of courses.
 */
public class Minor extends AcademicEntity implements Serializable {
    private List<Course> courses; 

    // constructor to initialize minor name and course list
    public Minor(String name) {
        super(name);
        this.courses = new ArrayList<>();
    }

    public List<Course> getCourses() {
        return courses;
    }

    // adds a course to the minor
    public void addCourse(Course course) {
        courses.add(course);
    }

    // Prints courses and calculates grades needed to pass
    @Override
    public void printCourses() {
        for (Course course : courses) {
            double grade = course.getRandomGrade();
            System.out.println("Course: " + course.getCourseName() + ", Grade: " + String.format("%.2f", grade) + "%");
            double requiredGrade = 50.00 - grade;
            if (requiredGrade > 0) {
                System.out.println("You need " + String.format("%.2f", requiredGrade) + "% more to pass this course.");
            } else {
                System.out.println("You have already passed this course!\n");
            }
            System.out.println("Good luck!\n");
        }
    }
}
