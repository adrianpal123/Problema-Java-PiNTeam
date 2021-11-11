package com.company.Vacation_Hire_Inc;

public class Minivan extends Car {
    public Minivan() {
        super(50);
        this.number_of_vehicles = 7;
        this.daily_percentage_fee = 0.005F;
        this.value_USD = 60000;


    }

    @Override
    public float extraChargingFees() {
        return super.extraChargingFees();
    }

    @Override
    public void setNumber_of_vehicles(int number_of_vehicles) {
        super.setNumber_of_vehicles(number_of_vehicles);
    }

    @Override
    public int numberOfAvailableRentals() {
        return this.number_of_vehicles;
    }

    @Override
    public void customerReturnsRentedItem() {
        super.customerReturnsRentedItem();
        this.number_of_vehicles ++;
        System.out.println("The customer came back with the MINIVAN, we now " + this.number_of_vehicles + " minivans in our rental shop.");
    }

    @Override
    public void customerRentsItem() {
        super.customerRentsItem();
        if (this.number_of_vehicles == 0)
            System.out.println("There are no more Minivans left! Pick another type of car");
        else {
            this.number_of_vehicles--;
            System.out.println("A MINIVAN has been rented, we now have " + this.number_of_vehicles + " Minivans left.");
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
