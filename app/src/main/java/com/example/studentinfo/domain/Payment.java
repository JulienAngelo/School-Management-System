package com.example.studentinfo.domain;

public class Payment {

    String term,student_indexNo;
     int balance,full_payment;
     int payment = 0;
    String[] days = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
    public Payment(String term, String student_indexNo, int balance, int payment, int full_payment) {
        this.term = term;
        this.student_indexNo = student_indexNo;
        this.balance = balance;
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

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getFull_payment() {
        return full_payment;
    }

    public void setFull_payment(int full_payment) {
        this.full_payment = full_payment;
    }

    public String toString(){
        return String.valueOf(payment);
    }

    public String[] getDays() {
        return days;
    }

    public void setDays(String[] days) {
        this.days = days;
    }
}
