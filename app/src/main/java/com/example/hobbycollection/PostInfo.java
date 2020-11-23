package com.example.hobbycollection;

public class PostInfo {
    private String order;
    private String name;
    private String phoneNumber;
    private String number;
    private String publisher;

    public PostInfo(String order, String name, String phoneNumber, String number, String publisher) {
        this.order = order;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.number = number;
        this.publisher = publisher;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
