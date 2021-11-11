package com.company.Vacation_Hire_Inc;

public interface Rental {

    // methods that can be implemented in the future to any type of rental. we only rent a kind of vehicles now -> cars.
    // the methods are being implemented in the abstract class vehicle and class car.

    float extraChargingFees();

    float basicDailyFee();

    int numberOfAvailableRentals();

    void customerReturnsRentedItem();

    void customerRentsItem();





}
