package com.example.studentinfo.Database;
/**
 * SignUpHelper
 *
 ************************************
 *  ###   Date         Batch            Group Id      Group     Author          Description     ITNumber
 *-------------------------------------------------------------------------------------------------------
 *    1   03-10-2020   Metro-Weekend    Metro_WE_05   Maniacs   Yoganathan.J.A    Created       IT19067902
 *
 ************************************
 */
public class SignUpHelper {

    String AdmissionNo, StudentFullName, StudentDOB, ParentNIC, ParentName, ParentContact, Address, AdmissionDate;

    public SignUpHelper() {
    }
    public SignUpHelper(String admissionNo, String studentFullName, String studentDOB, String parentNIC, String parentName, String parentContact, String address, String admissionDate) {
        this.AdmissionNo = admissionNo;
        this.StudentFullName = studentFullName;
        this.StudentDOB = studentDOB;
        this.ParentNIC = parentNIC;
        this.ParentName = parentName;
        this.ParentContact = parentContact;
        this.Address = address;
        this.AdmissionDate = admissionDate;
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
}
