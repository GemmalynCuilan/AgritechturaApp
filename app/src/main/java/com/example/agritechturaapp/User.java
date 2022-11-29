package com.example.agritechturaapp;

public class User {
    public String fullname, email, pass, profileImage;


        public User() {
        }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public User(String fullname, String email, String pass, String profileImage) {
            this.fullname = fullname;
            this.email = email;
            this.pass = pass;
            this.profileImage = profileImage;

        }

}

