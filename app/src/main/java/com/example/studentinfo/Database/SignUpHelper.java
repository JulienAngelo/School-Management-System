package com.example.studentinfo.Database;

public class SignUpHelper {

    String AdmissionNo, StudentFullName, StudentDOB, ParentNIC, ParentName, ParentContact, Address, AdmissionDate;

    private String purl;
    private String status;
    private String createdDate;
    private String firstTermAmount;
    private String secondTermAmount;
    private String thirdTermAmount;
    private String fullAmount;
    private String password;

    public SignUpHelper() {
    }

    /*public SignUpHelper(String admissionNo, String studentFullName, String studentDOB, String parentNIC, String parentName, String parentContact, String address, String admissionDate) {
        this.AdmissionNo = admissionNo;
        this.StudentFullName = studentFullName;
        this.StudentDOB = studentDOB;
        this.ParentNIC = parentNIC;
        this.ParentName = parentName;
        this.ParentContact = parentContact;
        this.Address = address;
        this.AdmissionDate = admissionDate;
    }*/

    public SignUpHelper(String admissionNo, String studentFullName, String studentDOB, String parentNIC, String parentName, String parentContact, String address, String admissionDate, String purl, String status, String createdDate, String firstTermAmount, String secondTermAmount, String thirdTermAmount, String fullAmount, String password) {
        this.AdmissionNo = admissionNo;
        this.StudentFullName = studentFullName;
        this.StudentDOB = studentDOB;
        this.ParentNIC = parentNIC;
        this.ParentName = parentName;
        this.ParentContact = parentContact;
        this.Address = address;
        this.AdmissionDate = admissionDate;
        this.purl = purl;
        this.status = status;
        this.createdDate = createdDate;
        this.firstTermAmount = firstTermAmount;
        this.secondTermAmount = secondTermAmount;
        this.thirdTermAmount = thirdTermAmount;
        this.fullAmount = fullAmount;
        this.password = password;
    }

    public String getAdmissionNo() {
        return AdmissionNo;
    }

    public void setAdmissionNo(String admissionNo) {
        this.AdmissionNo = admissionNo;
    }

    public String getStudentFullName() {
        return StudentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.StudentFullName = studentFullName;
    }

    public String getStudentDOB() {
        return StudentDOB;
    }

    public void setStudentDOB(String studentDOB) {
        this.StudentDOB = studentDOB;
    }

    public String getParentNIC() {
        return ParentNIC;
    }

    public void setParentNIC(String parentNIC) {
        this.ParentNIC = parentNIC;
    }

    public String getParentName() {
        return ParentName;
    }

    public void setParentName(String parentName) {
        this.ParentName = parentName;
    }

    public String getParentContact() {
        return ParentContact;
    }

    public void setParentContact(String parentContact) {
        this.ParentContact = parentContact;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getAdmissionDate() {
        return AdmissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.AdmissionDate = admissionDate;
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
