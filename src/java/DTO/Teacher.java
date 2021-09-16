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
public class Teacher {
    private String id;
    private String ln;
    private String fn;

    public Teacher() {
        id="";
        ln="";
        fn="";
    }

    public Teacher(String id, String ln, String fn) {
        this.id = id;
        this.ln = ln;
        this.fn = fn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLn() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }
    
    
    
}
