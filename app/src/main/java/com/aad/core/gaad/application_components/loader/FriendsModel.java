package com.aad.core.gaad.application_components.loader;

public class FriendsModel {

    private String name;
    private boolean gender;

    public FriendsModel(String name, boolean gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
