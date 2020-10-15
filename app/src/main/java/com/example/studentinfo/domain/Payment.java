package com.example.studentinfo.domain;

public class Payment {
    String term, student_indexNo;
    String  full_payment;
    String payment;
    public Payment(String term, String student_indexNo, String payment, String full_payment) {
        this.term = term;
        this.student_indexNo = student_indexNo;
        this.payment = payment;
        this.full_payment = full_payment;
    }
    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getStudent_indexNo() {
        return student_indexNo;
    }

    public void setStudent_indexNo(String student_indexNo) {
        this.student_indexNo = student_indexNo;
    }
    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getFull_payment() {
        return full_payment;
    }

    public void setFull_payment(String full_payment) {
        this.full_payment = full_payment;
    }
}
