package com.example.studentinfo.domain;

public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    private String purl;
    private String status;
    private String createdDate;
    private String firstTermAmount;
    private String secondTermAmount;
    private String thirdTermAmount;
    private String fullAmount;
    private String password;
    private String mobileNumber;

    public Student() {

    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getFirstTermAmount() {
        return firstTermAmount;
    }

    public void setFirstTermAmount(String firstTermAmount) {
        this.firstTermAmount = firstTermAmount;
    }

    public String getSecondTermAmount() {
        return secondTermAmount;
    }

    public void setSecondTermAmount(String secondTermAmount) {
        this.secondTermAmount = secondTermAmount;
    }

    public String getThirdTermAmount() {
        return thirdTermAmount;
    }

    public void setThirdTermAmount(String thirdTermAmount) {
        this.thirdTermAmount = thirdTermAmount;
    }

    public String getFullAmount() {
        return fullAmount;
    }

    public void setFullAmount(String fullAmount) {
        this.fullAmount = fullAmount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
