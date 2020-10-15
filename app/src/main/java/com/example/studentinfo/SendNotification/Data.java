package com.example.studentinfo.SendNotification;

public class Data {
    private String studentId;
    private String description;

    public Data(String title, String message){
        studentId = title;
        description = message;
    }
    public Data(){

    }
    public String getTitle(){
        return studentId;
    }
    public void setTitle(String title){
        studentId = title;
    }
    public String getMessage(){
        return description;
    }
    public void setMessage(String message){
        description = message;
    }
}
