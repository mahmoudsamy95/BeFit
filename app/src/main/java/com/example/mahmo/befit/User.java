package com.example.mahmo.befit;

/**
 * Created by mahmo on 11/30/2017.
 */

public class User {
    String username;
    String email;
    String age;
    String gender;
    int calories;
    int steps;

    public User(String username, String email, String age, String gender) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }

    public User(String username, String email, String age, String gender, int calories, int steps) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.calories = calories;
        this.steps = steps;
    }

    public User() {
    }

    public User(String email) {
        this.email = email;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}
