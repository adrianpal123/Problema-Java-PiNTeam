package com.company.Vacation_Hire_Inc;

public class Sedan extends Car{

    public Sedan() {
        super(20);
        this.number_of_vehicles = 10;
        this.daily_percentage_fee = 0.003F;
        this.value_USD = 25000;
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
    public float basicDailyFee() {
        return this.daily_percentage_fee * this.value_USD;
    }


    @Override
    public void customerReturnsRentedItem() {
        super.customerReturnsRentedItem();
        this.number_of_vehicles ++;
        System.out.println("The customer came back with the SEDAN, we now " + this.number_of_vehicles + " sedans in our rental shop.");
    }

    @Override
    public void customerRentsItem() {
        super.customerRentsItem();
        if (this.number_of_vehicles == 0)
            System.out.println("There are no more Sedans left! Pick another type of car");
        else {
            this.number_of_vehicles--;
            System.out.println("A SEDAN has been rented, we now have " + this.number_of_vehicles + " sedans left.");
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
