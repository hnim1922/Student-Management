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
public class Subject {
    private String id;
    private String name;
    private boolean ava;
    private String url;
    public Subject() {
        id="";
        name="";
        ava=false;
        url="";
    }

    public Subject(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public Subject(String id, boolean ava, String url) {
        this.id = id;
        this.ava = ava;
        this.url = url;
    }
    public Subject(String id, String name, boolean ava, String url) {
        this.id = id;
        this.name = name;
        this.ava = ava;
        this.url = url;
    }

    public boolean getAva() {
        return ava;
    }

    public void setAva(boolean ava) {
        this.ava = ava;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
