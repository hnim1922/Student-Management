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
public class Class {
    private String id;
    private String name;
    private String attendants;

    public Class() {
        id="";
        name="";
        attendants="";
    }

    public Class(String id, String name, String attendants) {
        this.id = id;
        this.name = name;
        this.attendants = attendants;
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

    public String getAttendants() {
        return attendants;
    }

    public void setAttendants(String attendants) {
        this.attendants = attendants;
    }
}
