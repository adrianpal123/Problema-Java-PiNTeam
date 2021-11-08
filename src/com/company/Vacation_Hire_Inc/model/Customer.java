package com.company.Vacation_Hire_Inc.model;


public class Customer {

    //each order will be unique to each costumer by the the phone number
    private String phone;
    private String name;

    public Customer(String phone)
    {
        this.phone = phone;
    }
    public Customer(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
