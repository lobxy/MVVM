package com.lovish.mvvmapp.main.model;

import java.io.Serializable;

public class UserData implements Serializable {

    private int id;
    private String name;
    private String image;
    private String lastName;
    private boolean checked;

    public UserData(int id, String name, String lastName, boolean checked, String image) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.checked = checked;
        this.image = image;
    }

    public UserData() {

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
