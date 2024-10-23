/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package academic;

import java.io.Serializable;

/**
 * Represents a base entity for academic elements with a name.
 * all academic entities should extend this class.
 */
public abstract class AcademicEntity implements Serializable {
    private String name; // The name of the academic entity

    // constructor to initialize the name of the entity
    public AcademicEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // abstract method for printing courses, to be implemented by subclasses
    public abstract void printCourses();
}
