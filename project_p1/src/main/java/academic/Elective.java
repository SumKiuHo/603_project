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
 * Represents an elective with a list of courses.
 */
public class Elective extends AcademicEntity implements Serializable {
    private List<Course> courses; 

    // constructor to initialize elective name and course list
    public Elective(String name) {
        super(name);
        this.courses = new ArrayList<>();
    }

    public List<Course> getCourses() {
        return courses;
    }

    // this adds a course to the elective
    public void addCourse(Course course) {
        courses.add(course);
    }

    // Prints courses and calculates grades needed to pass
    @Override
    public void printCourses() {
        for (Course course : courses) {
            double grade = course.getRandomGrade();
            System.out.println("Course: " + course.getCourseName() + ", Grade: " + String.format("%.2f", grade) + "%\n");

            double requiredGrade = 50.00 - grade;
            if (requiredGrade > 0) {
                System.out.println("You need " + String.format("%.2f", requiredGrade) + "% more to pass this course.\n");
            } else {
                System.out.println("You have already passed this course!\n");
            }
            System.out.println("Good luck!");
        }
    }
}
