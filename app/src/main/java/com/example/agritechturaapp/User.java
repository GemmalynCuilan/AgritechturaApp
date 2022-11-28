package com.example.agritechturaapp;

public class User {
    public String name, emailAdd, pass, profileImage;


        public User() {
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAdd() {
        return emailAdd;
    }

    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
    public User(String name, String emailAdd, String pass, String profileImage) {
            this.name = name;
            this.emailAdd = emailAdd;
            this.pass = pass;
            this.profileImage = profileImage;

        }

}

