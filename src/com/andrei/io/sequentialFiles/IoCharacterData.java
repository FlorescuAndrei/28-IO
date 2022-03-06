package com.andrei.io.sequentialFiles;

import com.andrei.Customer;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class IoCharacterData {

    public static List<Customer> customers = new LinkedList<>();

    public static void main(String[] args) throws IOException {
    // if I do not use throw, I must surround with try catch block

//        loadCustomersFromMemory();
//        printCustomers();
//
//        saveCustomersToFile();

        loadCustomersFromFile();
        printCustomers();


    }



    public static List<Customer> loadCustomersFromMemory(){



        Customer jj = new Customer("Jane", "Jones", 000);
        Customer jd = new Customer("John", "Doe", 111);
        Customer ms = new Customer("Mary", "Smith", 222);
        Customer mw = new Customer("Mike", "Wilson", 333);

        customers.add(jj);
        customers.add(jd);
        customers.add(ms);
        customers.add(mw);

        return customers;
    }



    //save customers to file
    public static void saveCustomersToFile()throws IOException {
        //if I do not use throws, I must surround with try catch finally block, and in finally to close the buffer.

        try (BufferedWriter customersFile = new BufferedWriter(new FileWriter("customers.txt"))) {
            for (Customer customer : customers) {
                customersFile.write(customer.getFirstName() + ", " + customer.getLastName() + ", " + customer.getId() + "\n");

            }
        }
    }

    //load customer from file
    public static void loadCustomersFromFile()throws IOException{
        try(BufferedReader customersFile = new BufferedReader(new FileReader("customers.txt"))){
            String input;
            while((input = customersFile.readLine()) != null){

                String[]data = input.split(", ");
                String firstName = data[0];
                String lastName = data[1];
                int id = Integer.parseInt(data[2]);

                customers.add(new Customer(firstName, lastName, id));
            }
        }
    }



    public static void printCustomers(){
        for(Customer customer : customers){
            System.out.println(customer);
        }
    }
}
