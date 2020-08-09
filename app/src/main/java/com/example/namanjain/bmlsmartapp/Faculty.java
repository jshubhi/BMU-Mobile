package com.example.namanjain.bmlsmartapp;

public class Faculty {

    private String name,course,email;

    public Faculty(String name, String course, String email) {
        this.setName(name);
        this.setCourse(course);
        this.setEmail(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
