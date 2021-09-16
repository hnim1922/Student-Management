/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author ADMIN
 */
public class Student {
    private String id;
    private String lastName;
    private String firstName;
    private String majorId;
    private String semester;
    private Class classes;
    private String email;

    public Student() {
        id="";
        lastName="";
        firstName="";
        majorId="";
        semester="";
        classes=new Class();
        email="";
    }

    public Student(String id, String lastName, String firstName, String majorId, String semester, Class classes, String email) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.majorId = majorId;
        this.semester = semester;
        this.classes = classes;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Class getClasses() {
        return classes;
    }

    public void setClasses(Class classes) {
        this.classes = classes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
