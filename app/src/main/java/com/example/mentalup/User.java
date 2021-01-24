package com.example.mentalup;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private int score;

    public User() {

    }

    public User(String firstName, String lastName, String email, int score) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
