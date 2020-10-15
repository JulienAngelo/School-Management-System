package com.example.studentinfo.domain;

public class IssueReview {


String studentId,description ,spinner, purl;

    public IssueReview() {
    }

    public IssueReview(String studentId , String description, String spinner) {
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

    public String  getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
