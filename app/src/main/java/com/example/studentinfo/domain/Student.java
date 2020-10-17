package com.example.studentinfo.domain;

public class Student {
    String AdmissionNo, StudentFullName, StudentDOB, ParentNIC, ParentName, ParentContact, Address, AdmissionDate;

    private String purl;
    private String status;
    private String createdDate;
    private String firstTermAmount;
    private String secondTermAmount;
    private String thirdTermAmount;
    private String fullAmount;
    private String password;

    public Student() {

    }

    public String getAdmissionNo() {
        return AdmissionNo;
    }

    public void setAdmissionNo(String admissionNo) {
        AdmissionNo = admissionNo;
    }

    public String getStudentFullName() {
        return StudentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        StudentFullName = studentFullName;
    }

    public String getStudentDOB() {
        return StudentDOB;
    }

    public void setStudentDOB(String studentDOB) {
        StudentDOB = studentDOB;
    }

    public String getParentNIC() {
        return ParentNIC;
    }

    public void setParentNIC(String parentNIC) {
        ParentNIC = parentNIC;
    }

    public String getParentName() {
        return ParentName;
    }

    public void setParentName(String parentName) {
        ParentName = parentName;
    }

    public String getParentContact() {
        return ParentContact;
    }

    public void setParentContact(String parentContact) {
        ParentContact = parentContact;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAdmissionDate() {
        return AdmissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        AdmissionDate = admissionDate;
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
}
