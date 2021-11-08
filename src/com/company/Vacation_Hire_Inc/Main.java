package com.company.Vacation_Hire_Inc;

import com.company.Vacation_Hire_Inc.enums.CarType;
import com.company.Vacation_Hire_Inc.interfaces.Rental;
import com.company.Vacation_Hire_Inc.model.Car;
import com.company.Vacation_Hire_Inc.model.Customer;
import com.company.Vacation_Hire_Inc.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<Customer> customers_list = new ArrayList<>();
    public static List<Order> orders_list = new ArrayList<>();


    public static void addOrder()
    {
        System.out.println("Let's create a new Order\n..");
        Customer customer;
        Rental rental;
        Order order;
        String number = existingCustomer();
        if(number.equals(""))
        {
          System.out.println("Customer not ok");
        }
        else
        {
            System.out.println("Customer ok");
            Scanner input = new Scanner(System.in);
            String customer_car_type = null;
            boolean ok = true;
            while (ok) {
                System.out.println("We only rent cars at the moment, what kind of car would the customer want to rent?");
                System.out.println("1 - Sedan");
                System.out.println("2 - Minivan");
                System.out.println("3 - Truck");

                switch (input.nextLine()) {
                    case "1":
                        customer_car_type = "SEDAN";
                        ok = false;
                        break;
                    case "2":
                        customer_car_type = "MINIVAN";
                        ok = false;
                        break;
                    case "3":
                        customer_car_type = "TRUCK";
                        ok = false;
                        break;
                    default:
                        System.out.println("Command not found");
                }
            }

            rental = new Car(CarType.valueOf(customer_car_type));
            System.out.println("Car Type: " + CarType.valueOf(customer_car_type));
            System.out.println("What's the price of the car the customer wants to rent? Type in USD.");

            while (true)
            {
                String price = input.nextLine();
                boolean alldigits = price.replaceAll("\\s", "").chars().allMatch(Character::isDigit);
                if (!alldigits)
                    System.out.println("Typo: Please make sure you type a number that represents the price of the car in USD");
                else {
                    ((Car) rental).setValue_USD(Float.parseFloat(price));
                    System.out.println("Price of the " + customer_car_type + " is " + price);
                    System.out.println("There is a rental of a " + customer_car_type + " on the following phone number: " + number);
                    break;
                }
            }

            System.out.println("Enter when the START date of the rental - MUST BE OF FORM: day-month-year. EX: 07-05-2020 ");
            String start_date = input.nextLine();
            System.out.println("Enter when END date of the rental - MUST BE OF FORM: day-month-year. EX: 08-05-2020 ");
            String end_date = input.nextLine();

            customer = getCustomerByPhoneNumber(number,customers_list);
            order = new Order(rental, customer, start_date, end_date);
            orders_list.add(order);

            assert customer != null;
            System.out.println("\n --------------------\nORDER MADE: \n" + "NAME: " + customer.getName() + "\nPHONE NUMBER: " + customer.getPhone());
            System.out.println("CAR TYPE: " + customer_car_type);
            System.out.println("START DATE FOR RENTAL: " + order.getRental_start_date() + "\nEND DATE FOR RENTAL: " + order.getRental_end_Date());
            System.out.println("A RENT FOR A TOTAL OF: " + order.getTotalRentalDays(start_date,end_date) + " DAYS");
            System.out.println("The customer has to pay a total of: " + order.getBill() + ", that's if the car is not damaged and tank is full");
            System.out.println("Added the order to our database");
        }
    }

    public static void listOrders(List<Order> myList)
    {
        System.out.println("List of all the orders: ");
        for (Order order : myList)
            System.out.println(order.toString());

    }
    public static void updateOrders()
    {
        Scanner scanner = new Scanner(System.in);
        Customer customer = null;
        Rental rental;
        Order order;
        String number;
        System.out.println("Customer comes in after the rental period is over");
        System.out.println("We need to make sure we make the correct bill");
        while (true)
        {
            System.out.print("Enter the phone Number: ");
            number = scanner.nextLine();
            boolean alldigits = number.replaceAll("\\s", "").chars().allMatch(Character::isDigit);
            if (!alldigits)
                System.out.println("Typo: Please make sure your phone number is a valid one");
            else {
                System.out.println("Phone Number: " + number);
                break;
            }
        }
        if (getCustomerByPhoneNumber(number,customers_list) != null) {
            customer = getCustomerByPhoneNumber(number,customers_list);
            System.out.println("Customer with the number found");
        }
        else
            System.out.println("Customer was not found");

        order = getOrderByCustomer(customer);
        rental = order.getRental();
        boolean ok = true;
        String digit;

        while (ok) {
            System.out.println("CHECK TO SEE: ");
            System.out.println("IS THE CAR DAMAGED?");
            System.out.println("1. YES");
            System.out.println("2. NO");
            digit = scanner.nextLine();
            switch (digit) {
                case "1":
                    ((Car) rental).setDamaged(true);
                    System.out.println("The car is damaged, the rent fee will increase");
                    ok = false;
                    break;
                case "2":
                    ((Car) rental).setDamaged(false);
                    System.out.println("The car is in good shape");
                    ok = false;
                    break;
                default:
                    System.out.println("Command not found");
                    ok = false;
            }
        }

        ok = true;
        System.out.println("CHECK TO SEE: ");
        while (ok) {
            System.out.println("IS THE CAR'S TANK FULL?");
            System.out.println("1. YES");
            System.out.println("2. NO");
            digit = scanner.nextLine();
            switch (digit) {
                case "1":
                    ((Car) rental).setFilled(true);
                    System.out.println("The car's tank is full");
                    ok = false;
                    break;
                case "2":
                    ((Car) rental).setFilled(false);
                    System.out.println("The car's tank is not filled up, our rent fee will increase");
                    ok = false;
                    break;
                default:
                    System.out.println("Command not found");
                    ok = false;
                    break;
            }
        }
        System.out.println("The bill increased from: " + order.getBasicBill() + " to: " + order.getBill());
        System.out.println("We will remove the order from our database");
        orders_list.remove(order);


    }

    public static Order getOrderByCustomer(Customer customer)
    {
        for (Order order : orders_list)
                if (order.getCustomer().equals(customer))
                    return order;
        return null;
    }

    public static void showAllRentedVehicles()
    {

    }


    public static void newCustomer()
    {
        Scanner scanner = new Scanner(System.in);  // initializing a scanner in order to be able to take input.
        String name;
        String number;
        // making sure the input name is in the right format and then adding it customer.
        while (true)
        {
            System.out.print("Enter the name: ");
            name = scanner.nextLine();
            boolean allLetters = name.replaceAll("\\s", "").chars().allMatch(Character::isLetter);
            if (!allLetters)
                System.out.println("Typo: Please make sure your name does not contain any Digits!");
            else {
                System.out.println("Name: " + name);
                break;
            }
        }

        // making sure the input phone number is in the right format and then adding it customer.
        while (true)
        {
            System.out.print("Enter the phone Number: ");
            number = scanner.nextLine();
            boolean alldigits = number.replaceAll("\\s", "").chars().allMatch(Character::isDigit);
            if (!alldigits)
                System.out.println("Typo: Please make sure your phone number does not contain any Letters!");
            else {
                System.out.println("Phone Number: " + number);
                break;
            }
        }
        Customer customer = new Customer(number,name);        // creating a new instance of a customer.

        //adding the newly made customer to out customer_list.
        customers_list.add(customer);

        System.out.println("\n --- Added a new customer to our database! ---\n");
        System.out.println("{ \n Customer's Name: " + customer.getName() + ";" + "\n Customer's Phone Number: " + customer.getPhone() +" \n}");

        menuSelection();
    }

    public static String existingCustomer()
    {
        // if we can't find the phone number, we will be asked to write a new one.
        // each customer has a phone number that is directly linked to their Order.

        System.out.println("Searching the database for a customer with the specified phone number");
        System.out.println( "...");

        Scanner scanner = new Scanner(System.in);
        String number;
        while (true) {
            System.out.println("Please enter a Phone Number: ");
            number = scanner.nextLine();
            boolean allLetters = number.replaceAll("\\s", "").chars().allMatch(Character::isDigit);
            if (!allLetters)
            {System.out.println("Typo: Please make sure your phone number does not contain any Letters!");
                return "";
            }
            else {
                System.out.println("Phone Number: " + number);
                break;
            }
        }
            boolean found = false;
            for (Customer customer : customers_list)
            {
                if (customer.getPhone().equals(number)) {
                    found = true;
                    System.out.println("Customer " + customer.getName() + " with the following phone number: " + number + " has been found");
                }
            }

                if (!found)
                {//existingCustomer();
                    System.out.println("No costumer found with that phone number! No order was made on that phone number");
                     return "";}
                else return number;
    }

    public static Customer getCustomerByPhoneNumber(String phone, List<Customer> my_list)
    {
        for (Customer customer : my_list)
            if (customer.getPhone().equals(phone))
                return customer;
        return null;
    }
    public static void readPhoneNumberFromInput(String number)
    {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter a Phone Number: ");
            number = scanner.nextLine();
            boolean allLetters = number.replaceAll("\\s", "").chars().allMatch(Character::isDigit);
            if (!allLetters)
            {System.out.println("Typo: Please make sure your phone number does not contain any Letters!");
            }
            else {
                System.out.println("Phone Number: " + number);
                break;
            }
        }
    }
    public static void readNameFromInput(String name)
    {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.print("Enter the name: ");
            name = scanner.nextLine();
            boolean allLetters = name.replaceAll("\\s", "").chars().allMatch(Character::isLetter);
            if (!allLetters)
                System.out.println("Typo: Please make sure your name does not contain any Digits!");
            else {
                System.out.println("Name: " + name);
                break;
            }
        }
    }

    public static Order getOrderByCustomer(List<Order> my_list, String phone, List<Customer> customers)
    {
        for (Order order : my_list)
            if (order.getCustomer().equals(getCustomerByPhoneNumber(phone,customers)))
                return order;
        return null;

    }

    public static void menuSelection() {
        Scanner input = new Scanner(System.in);
        String selection;

        //checking the user enters the correct number.

        do {
            System.out.println("\nChoose an option from the following: ");
            System.out.println("-----------------------------------");
            System.out.println("1 - New Client");
            System.out.println("2 - Check for existing Client");
            System.out.println("3 - Create an Order for a Customer");
            System.out.println("4 - List Orders");
            System.out.println("5 - Update list Orders");
            System.out.println("6 - List all rented vehicles");
            System.out.println("7 - Exit");
            System.out.println("-----------------------------------");


            selection = input.nextLine();
            switch (selection)
            {
                case "1":
                    newCustomer();
                    break;
                case "2":
                    existingCustomer();
                    break;
                case "3":
                    addOrder();
                    break;
                case "4":
                    listOrders(orders_list);
                    break;
                case "5":
                    updateOrders();
                    break;
                case  "6":
                    showAllRentedVehicles();
                    break;
                case "7":
                    System.out.println("BYE!");
                    return;
                default:
                    System.out.println("Command not found");
            }
        }while (true);

    }

    public static void main(String[] args) {

        menuSelection();

        }
}
