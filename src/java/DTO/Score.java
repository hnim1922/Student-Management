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
public class Score {
    private Student student;
    private Subject subject;
    private String score;
    private String semester;
    private String status;

    public Score() {
        student=new Student();
        subject=new Subject();
        score="";
        semester="";
        status="";
    }

    public Score(Student student, Subject subject, String score, String semester, String status) {
        this.student = student;
        this.subject = subject;
        this.score = score;
        this.semester = semester;
        this.status = status;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
