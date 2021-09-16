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
public class Register_Class {
    private Student student;
    private Subject subject;
    private String semester;

    public Register_Class() {
        student=new Student();
        subject=new Subject();
        semester="";
    }

    public Register_Class(Student student, Subject subject, String semester) {
        this.student = student;
        this.subject = subject;
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    
}
