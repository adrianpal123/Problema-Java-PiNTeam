package com.company.Vacation_Hire_Inc.model;

import com.company.Vacation_Hire_Inc.interfaces.Rental;

public abstract class Vehicle implements Rental {

    boolean damaged;
    boolean motorized_vehicle;
    int number_of_wheels;

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
}
