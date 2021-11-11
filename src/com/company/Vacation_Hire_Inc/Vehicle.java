package com.company.Vacation_Hire_Inc;


public abstract class Vehicle implements Rental {

    protected boolean damaged;
    protected boolean motorized_vehicle;
    protected int number_of_wheels;
    protected float daily_percentage_fee; //  daily_fee * number_of_days the vehicle is rented, in order to get calculate the fee;
    protected float value_USD; // price of the vehicle in USD
    protected int number_of_vehicles;

    public Vehicle(boolean damaged, boolean motorized_vehicle, int number_of_wheels) {
        this.damaged = damaged;
        this.motorized_vehicle = motorized_vehicle;
        this.number_of_wheels = number_of_wheels;
        daily_percentage_fee = 0.001F;
    }

    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }

    public void setMotorized_vehicle(boolean motorized_vehicle) {
        this.motorized_vehicle = motorized_vehicle;
    }

    public void setNumber_of_wheels(int number_of_wheels) {
        this.number_of_wheels = number_of_wheels;
    }

    public void setDaily_percentage_fee(float daily_percentage_fee) {
        this.daily_percentage_fee = daily_percentage_fee;
    }

    public void setValue_USD(float value_USD) {
        this.value_USD = value_USD;
    }

    public void setNumber_of_vehicles(int number_of_vehicles) {
        this.number_of_vehicles = number_of_vehicles;
    }

    // implements methods from Rental interface
    @Override
    public int numberOfAvailableRentals() {
        return 0;
    }

    @Override
    public float extraChargingFees() {
        return 0;
    }

    @Override
    public float basicDailyFee() {
        return 0;
    }

    @Override
    public void customerReturnsRentedItem() {
        System.out.println("Customer returns the rented vehicle.");
    }

    @Override
    public void customerRentsItem() {
        System.out.println("Customer wants to rent a vehicle.");
    }


}
