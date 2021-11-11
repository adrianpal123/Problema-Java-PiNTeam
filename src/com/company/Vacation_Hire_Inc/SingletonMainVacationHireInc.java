package com.company.Vacation_Hire_Inc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SingletonMainVacationHireInc {

    private List<Customer> customers_list = new ArrayList<>();
    private List<Order> orders_list = new ArrayList<>();
    private List<Rental> rentals_list = new ArrayList<>();

    //Even if two different employees access the Singleton class at the same time, right to the millisecond, it won't be a problem.
    private static volatile SingletonMainVacationHireInc single_instance = null;

    // private constructor, so it cannot be instantiated outside this class.
    private SingletonMainVacationHireInc() {

        // prevent form the reflection api.
        if (single_instance != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }

    }


    // check if the instance is null, within a synchronized block. If so, create the object
    public static SingletonMainVacationHireInc getInstance() {
        synchronized (SingletonMainVacationHireInc.class) {
            if (single_instance == null) {
                single_instance = new SingletonMainVacationHireInc();
            }
        }
        return single_instance;
    }




    /**
     * Case 1 - New Customer
     */
    public void newCustomer()
    {
        String name = null;
        String number = null;
        name = readNameFromInput(name);// making sure the input name is in the right format and then adding it customer.
        number=readPhoneNumberFromInput(number);// making sure the input phone number is in the right format and then adding it customer.
        Customer customer = new Customer(number,name);// creating a new instance of a customer.
        customers_list.add(customer);//adding the newly made customer to out customer_list.
        System.out.println("\n --- Added a new customer to our database! ---\n");
        System.out.println("{ \n Customer's Name: " + customer.getName() + ";" + "\n Customer's Phone Number: " + customer.getPhone() +" \n}");

        menuSelection();
    }

    /**
     * Case 2 - Check for an existing Customer
     * @return customer's phone number
     */
    public String existingCustomer()
    {
        // if we can't find the phone number, the employee will be asked to write a new one.
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
        {
            System.out.println("No costumer found with that phone number! No order was made on that phone number");
            return "";}
        else return number;
    }

    /**
     * Case 3 - Create an Order for a Customer
     */
    public void addOrder()
    {
        System.out.println("Let's create a new Order\n..");
        Customer customer;
        Rental rental = null;
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
                        rental = new Sedan();
                        ok = false;
                        break;
                    case "2":
                        customer_car_type = "MINIVAN";
                        rental = new Minivan();
                        ok = false;
                        break;
                    case "3":
                        customer_car_type = "TRUCK";
                        rental = new Truck();
                        ok = false;
                        break;
                    default:
                        System.out.println("Command not found");
                }
            }

            if (rental.numberOfAvailableRentals() == 0) {
            rental.customerRentsItem();
            menuSelection();
            }
            else {
                rental.customerRentsItem(); // one car leaves the parking lot
            }
            System.out.println("Enter when the START date of the rental - MUST BE OF FORM: day-month-year. EX: 07-05-2020 - DAY-MONTH-YEAR ");
            String start_date = input.nextLine();
            System.out.println("Enter when END date of the rental - MUST BE OF FORM: day-month-year. EX: 08-05-2020 - DAY-MONTH-YEAR ");
            String end_date = input.nextLine();

            customer = getCustomerByPhoneNumber(number);
            order = new Order(rental, customer, start_date, end_date);
            this.orders_list.add(order);

            assert customer != null;
            System.out.println("\n --------------------\nORDER MADE: \n" + "NAME: " + customer.getName() + "\nPHONE NUMBER: " + customer.getPhone());
            System.out.println("CAR TYPE: " + customer_car_type);
            System.out.println("START DATE FOR RENTAL: " + order.getRental_start_date() + "\nEND DATE FOR RENTAL: " + order.getRental_end_Date());
            System.out.println("A RENT FOR A TOTAL OF: " + order.getTotalRentalDays(start_date,end_date) + " DAYS");
            System.out.println("The customer has to pay a total of: " + order.getBill() + ", that's if the car is not damaged and tank is full");
            System.out.println("Added the order to our database");
        }
    }

    /**
     * Case 4 - List Orders
     * @param myList list of orders
     */
    public void listOrders(List<Order> myList)
    {
        System.out.println("List of all the orders: ");
        for (Order order : myList)
            System.out.println(order.toString());

    }

    /**
     * Case 5 - Update list Orders
     */
    public void updateOrders()
    {
        Scanner scanner = new Scanner(System.in);
        Customer customer = null;
        Rental rental = null;
        Order order = null;
        String number = null;
        System.out.println("Customer comes in after the rental period is over");
        System.out.println("We need to make sure we make the correct bill");
        number = readPhoneNumberFromInput(number);
        if (getCustomerByPhoneNumber(number) != null) {
            customer = getCustomerByPhoneNumber(number);
            System.out.println("Customer with the number found");
        }
        else {
            System.out.println("Customer was not found");
            menuSelection();
        }

        order = getOrderByCustomer(customer);
        rental = order.getRental();
        boolean ok = true;
        String digit;

        while (ok) {
            System.out.println("CHECK TO SEE: ");
            System.out.println("IS THE CAR DAMAGED?");
            System.out.println("1 - YES");
            System.out.println("2 - NO");
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
        rental.customerReturnsRentedItem(); // number of the cars that we have in the parking lot increases after the customer returns it.
        this.orders_list.remove(order); // we remove the order from the order list.


    }

    /**
     * Case 6 - List all rented vehicles
     */
    public void showAllRentedVehicles()
    {
        System.out.println("All the vehicles that are being rented now: ");
        for (Order order : this.orders_list)
        {
            System.out.println();
            System.out.println("--------------------");
            System.out.println("Customer's name: "+order.customer.getName()+" rented the following car type: ");
            if (order.getRental() instanceof Sedan)
                System.out.println("SEDAN ");
            if (order.getRental() instanceof Minivan)
                System.out.println("MINIVAN ");
            if (order.getRental() instanceof Truck)
                System.out.println("TRUCK ");
            System.out.println(" --Details of the car-- ");
            if (order.getRental() instanceof Car)
                System.out.println(((Car) order.getRental()));

            System.out.println("He has to pay: " + order.getBill() + ", that's if he comes in with a non-damaged car and a tank full of gasoline! ");
        }
    }

    /**
     * Returns the order from the orders_list that has a specified customer *used in updateOrders - Case 5
     * @param customer
     * @return
     */
    public Order getOrderByCustomer(Customer customer)
    {
        for (Order order : this.orders_list)
                if (order.getCustomer().equals(customer))
                    return order;
        return null;
    }

    /**
     * Returns a customer from the customers_list that has a specified phone number *used in addOrder - Case 3
     * @param phone customer's phone number
     * @return the customer with the specified phone number
     */
    public Customer getCustomerByPhoneNumber(String phone)
    {
        for (Customer customer : customers_list)
            if (customer.getPhone().equals(phone))
                return customer;
        return null;
    }

    /**
     * Makes sure the user puts in a valid phone number as an input *used in newCustomer() - Case 1, addOrder - Case 3, updateOrders - Case 5
     * @param number customer's phone number
     * @return the correct input string
     */
    public String readPhoneNumberFromInput(String number)
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
        return number;
    }

    /**
     * Makes sure the user puts in a valid name as an input *used in newCustomer() - Case 1
     * @param name
     * @return
     */
    public String readNameFromInput(String name)
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
        return name;
    }


    public void menuSelection() {
        Scanner input = new Scanner(System.in);
        String selection;

        //checking the user enters the correct number.

        do {
            System.out.println("\nChoose an option from the following: ");
            System.out.println("-----------------------------------");
            System.out.println("1 - New Customer");
            System.out.println("2 - Check for an existing Customer");
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
                    listOrders(this.orders_list);
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

        SingletonMainVacationHireInc main = SingletonMainVacationHireInc.getInstance();

        main.menuSelection();

        }
}