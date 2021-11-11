package com.company.Vacation_Hire_Inc;


public abstract class Car extends Vehicle {

    protected final static float gallon_price_usd = 3.5F; // assuming each gallon costs 3.5 $;
    protected boolean filled; // boolean that shows if a car is filled with gasoline or not; When we rent the cars, the gas tank is always full.
    protected int tank_size_gallons; // size of the car's tank, in gallons;



    public Car(int tank_size_gallons) {
        super(false,true,4);
        this.filled = true;
        this.tank_size_gallons = tank_size_gallons;
    }



    /**
     * Getter tank size
     * @return tank size in gallons.
     */
    public int getTank_size_gallons()
    {
        return this.tank_size_gallons;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * we want to get 30% of the car's worth if it has been damaged.
     * @return 30% of the car's total price
     */
    private float checkDamageStatus ()
    {
        if (this.damaged)
            return 3/10F * value_USD;
        return 0;
    }

    /**
     * in case the car is not filled with gasoline, we will add this to the bill;
     * @param tank_size_gallons tank size in gallons
     * @param gallon_price_usd price of a gallon
     * @return tank's size in gallons * price of the gallon USD
     */
    private float checkTankStatus (int tank_size_gallons, float gallon_price_usd)
    {
        if (!filled)
            return tank_size_gallons * gallon_price_usd;
        return 0;

    }

    /**
     * implements the method from the rental interface.
     * @return Daily fee for renting a car = 0.001/100 * value of the car
     */
    @Override
    public float basicDailyFee() {
        return daily_percentage_fee * value_USD;
    }

    /**
     * implements the method from the rental interface.
     * need to take into consideration: type of car that it is, weather it has a gasoline or not, and weather it is damaged or not;
     * add how many gallons can a car store * how much usd a gallon costs.
     * add 30% of the car's total worth, if it has been damaged.
     * @return extra rental fee
     */
    @Override
    public float extraChargingFees() {

        return  checkTankStatus(getTank_size_gallons(),gallon_price_usd) + checkDamageStatus();
    }


    /**
     * the number of rentable cars increases after the customer returns a car.
     */
    @Override
    public void customerReturnsRentedItem() {
        super.customerReturnsRentedItem();
        System.out.println("Customer returns the car after the rental period is over");
    }

    /**
     * the number of rentable cars decreases after the customer rents a car.
     * checks we are able to rent another car, that's if we have more than 0 cars.
     */
    @Override
    public void customerRentsItem() {
        super.customerRentsItem();
        System.out.println("Customer wants to rent the car");
        }

    @Override
    public String toString() {
        return "Car{" +
                "filled=" + filled +
                ", tank_size_gallons=" + tank_size_gallons +
                ", damaged=" + damaged +
                ", motorized_vehicle=" + motorized_vehicle +
                ", number_of_wheels=" + number_of_wheels +
                ", daily_percentage_fee=" + daily_percentage_fee +
                ", value_USD=" + value_USD +
                '}';
    }
}
