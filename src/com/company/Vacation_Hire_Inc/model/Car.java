package com.company.Vacation_Hire_Inc.model;


import com.company.Vacation_Hire_Inc.enums.CarType;


public class Car extends Vehicle {

    private final CarType type; // one of the three: SEDAN, MINIVAN, TRUCK;
    private final static float gallon_price_usd = 3.5F; // assuming each gallon costs 3.5 $;
    private final static float daily_percentage_fee = 0.01F; //  daily_fee * number_of_days the car is rented, in order to get calculate the fee;
    private float value_USD; // an estimation of the car's value;
    private boolean filled; // boolean that shows if a car is filled with gasoline or not; When we rent the cars, the gastank is always full.
    private int tank_size_gallons; // size of the car's tank, in gallons;

    public Car(CarType type)
    {
        this.type = type;
        //we always give a non-damaged car with a full tank;
        this.filled = true;
        this.damaged = false;
        switch (type)
        {
            case SEDAN:
                tank_size_gallons = 30;
                break;
            case MINIVAN:
                tank_size_gallons = 50;
                break;
            case TRUCK:
                tank_size_gallons = 75;
                break;
            default:
                throw new IllegalStateException("Unknown enumeration value " + type);

        }
        motorized_vehicle = true;
        number_of_wheels = 4;
    }
    public Car(CarType type, float value_USD)
    {
        this.type = type;
        this.value_USD = value_USD;
        this.filled = true;
        this.damaged = false;
        motorized_vehicle = true;
        number_of_wheels = 4;
    }
    public Car(CarType type, float value_euros, boolean is_filled,boolean is_damaged) {
        this.type = type;
        this.value_USD = value_euros;
        this.filled = is_filled;
        damaged = is_damaged;
        motorized_vehicle = true;
        number_of_wheels = 4;

    }

    public int getTank_size_gallons()
    {
        return this.tank_size_gallons;
    }

    public void setDamaged (boolean damaged)
    {
        this.damaged = damaged;
    }

    public void setFilled(boolean filled)
    {
        this.filled = filled;
    }

    public void setValue_USD(float value_USD) {
        this.value_USD = value_USD;
    }

    public CarType getType() {
        return type;
    }

    // in case the car is not filled with gasoline, we will add this to the bill;
    private float checkTankStatus (int tank_size_gallons, float gallon_price_usd)
    {
        if (!filled)
            return tank_size_gallons * gallon_price_usd;
        return 0;

    }

    // we want to get 30% of the car's worth if it has been damaged;
    private float checkDamageStatus ()
    {
        if (this.damaged)
            return 3/10F * value_USD;
        return 0;
    }


    @Override
    public float basicDailyFee() {
        return daily_percentage_fee * value_USD;
    }


    // we implement the method from the rental interface.
    // we need to take into consideration: type of car that it is, weather it has a gasoline or not, and weather it is damaged or not;
    // we add how many gallons can a car store * how much usd a gallon costs.
    // we add 30% of the car's total worth if it is damaged.
    @Override
    public float extraChargingFees() {

        return  checkTankStatus(getTank_size_gallons(),gallon_price_usd) + checkDamageStatus();
    }

    @Override
    public int numberOfAvailableRentals() {
        return 10;
    }



    @Override
    public String toString() {
        return "Car{" +
                "type=" + type +
                ", value_USD=" + value_USD +
                ", filled=" + filled +
                ", tank_size_gallons=" + tank_size_gallons +
                ", damaged=" + damaged +
                ", motorized_vehicle=" + motorized_vehicle +
                ", number_of_wheels=" + number_of_wheels +
                '}';
    }
}
