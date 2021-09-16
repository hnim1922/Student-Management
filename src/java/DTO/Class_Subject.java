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
public class Class_Subject {
    private Class classes;
    private Subject subject;
    private String semester;
    private Teacher teacher;

    public Class_Subject() {
        classes=new Class();
        subject=new Subject();
        semester="";
        teacher=new Teacher();
    }

    public Class_Subject(Class classes, Subject subject, String semester, Teacher teacher) {
        this.classes = classes;
        this.subject = subject;
        this.semester = semester;
        this.teacher = teacher;
    }

    public Class getClasses() {
        return classes;
    }

    public void setClasses(Class Classes) {
        this.classes = classes;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    
}
