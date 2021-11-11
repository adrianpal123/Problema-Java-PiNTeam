package com.company.Vacation_Hire_Inc;


public class Customer {

    private String phone;
    private String name;

    /**
     *
     * @param phone each order will be unique to each costumer by the the phone number
     * @param name of the customer
     */
    public Customer(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
