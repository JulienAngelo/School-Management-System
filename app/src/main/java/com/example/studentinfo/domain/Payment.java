package com.example.studentinfo.domain;

import java.math.BigDecimal;

public class Payment {
    String studentId;
    String term;
    Float balance;
    Float fullPayment;
    Float payment;

    public Payment() {
    }

    public Payment(String studentId, String term, Float balance, Float fullPayment, Float payment) {
        this.studentId = studentId;
        this.term = term;
        this.balance = balance;
        this.fullPayment = fullPayment;
        this.payment = payment;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Float getFullPayment() {
        return fullPayment;
    }

    public void setFullPayment(Float fullPayment) {
        this.fullPayment = fullPayment;
    }

    public Float getPayment() {
        return payment;
    }

    public void setPayment(Float payment) {
        this.payment = payment;
    }
}
