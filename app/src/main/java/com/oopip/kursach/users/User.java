package com.oopip.kursach.users;

import java.io.Serializable;

public class User implements Serializable {
    private String name, pass, role, uid;
    private boolean accses;

    public User(String name, String pass, String role, String uid, boolean accses) {
        this.name = name;
        this.pass = pass;
        this.role = role;
        this.uid = uid;
        this.accses = accses;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAccses() {
        return accses;
    }

    public void setAccses(boolean accses) {
        this.accses = accses;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
