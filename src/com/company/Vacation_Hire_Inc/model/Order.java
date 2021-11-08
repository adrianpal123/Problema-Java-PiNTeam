package com.company.Vacation_Hire_Inc.model;

import com.company.Vacation_Hire_Inc.enums.CarType;
import com.company.Vacation_Hire_Inc.interfaces.Rental;
import com.company.Vacation_Hire_Inc.model.Customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Order {
    Rental rental;
    Customer customer;
    String rental_start_date;
    String rental_end_Date;

    public Order(Rental rental, Customer customer, String order, String return_date) {
        this.rental = rental;
        this.customer = customer;
        this.rental_start_date = order;
        this.rental_end_Date = return_date;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getRental_start_date() {
        return rental_start_date;
    }

    public String getRental_end_Date() {
        return rental_end_Date;
    }


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


    //basic daily fee * number of days + extrafeecharge();
    //extra fee charge for cars are: either it has been damaged or it has no gas left in the tank.
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
