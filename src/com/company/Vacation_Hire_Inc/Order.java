package com.company.Vacation_Hire_Inc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Order {
    Rental rental;
    Customer customer;
    String rental_start_date;
    String rental_end_Date;

    /**
     *
     * @param rental - a customer can rent, for now, the costumer can only rent different types of cars.
     * @param customer - comes to our rental company - Vacation Hire Inc
     * @param start_date - time when a customer starts renting the car
     * @param return_date - when the renting time is over and the customer brings in the car
     */
    public Order(Rental rental, Customer customer, String start_date, String return_date) {
        this.rental = rental;
        this.customer = customer;
        this.rental_start_date = start_date;
        this.rental_end_Date = return_date;
    }

    public Rental getRental() {
        return rental;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getRental_start_date() {
        return rental_start_date;
    }

    public String getRental_end_Date() {
        return rental_end_Date;
    }

    /**
     *
     * @param input_string1 start rental date as a string
     * @param input_string2 end rental date as a string
     * @return the amount of days the customer has rented the car
     */
    public long getTotalRentalDays(String input_string1, String input_string2) {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date1 = myFormat.parse(input_string1);
            Date date2 = myFormat.parse(input_string2);
            long diff = date2.getTime() - date1.getTime();
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }


    //basic daily fee * number of days + extra fee charge;
    //extra fee charge for cars are:  it has either been damaged or it has no gas left in the tank.

    /**
     * basic daily fee * number of days + extra fee charge;
     * extra fee charge for cars are: it has either been damaged or it has no gas left in the tank.
     * @return sum of these two fees
     */
    public float getBill()
    {
        return getBasicBill() + rental.extraChargingFees();
    }

    public float getBasicBill()
    {
        return rental.basicDailyFee() * getTotalRentalDays(getRental_start_date(),getRental_end_Date());
    }

    @Override
    public String toString() {
        return "Order{" +
                "rental=" + rental.toString() +
                ", customer=" + customer.toString() +
                ", rental_start_date=" + rental_start_date +
                ", rental_end_Date=" + rental_end_Date +
                '}';
    }
}
