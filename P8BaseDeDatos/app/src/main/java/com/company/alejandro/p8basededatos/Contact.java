package com.company.alejandro.p8basededatos;

public class Contact {

    int id;
    String name;
    String PhoneNumber;


    public Contact(){}
    public Contact(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        PhoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
