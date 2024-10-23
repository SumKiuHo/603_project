/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package academic;

import java.io.Serializable;
import java.util.Random;

/**
 * Represents an academic course with a name and a random grade generator.
 */
public class Course implements Serializable {
    private String courseName; 

   
    public Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    // this generates a random grade between 30% and 50%
    public double getRandomGrade() {
        Random random = new Random();
        return 30 + (random.nextDouble() * 20);
    }
}
