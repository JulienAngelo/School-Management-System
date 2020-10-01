package com.example.studentinfo.domain;

public class Issue {

int studentId;
String description ,spinner;


    public Issue(int studentId ,String description, String spinner) {
        this.studentId = studentId;
        this.description = description;
        this.spinner = spinner;
    }

    public String getSpinner() {
        return spinner;
    }

    public void setSpinner(String spinner) {
        this.spinner = spinner;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
