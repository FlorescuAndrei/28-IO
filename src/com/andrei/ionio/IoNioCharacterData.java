package com.andrei.ionio;

import com.andrei.Customer;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class IoNioCharacterData {

    public static List<Customer> customers = new LinkedList<>();

    public static void main(String[] args) throws IOException {
    // if I do not use throw, I must surround with try catch block

//        loadCustomersFromMemory();
//        printCustomers();
//
//        saveCustomersToFile();
//
//        loadCustomersFromFile();

        loadCustomersFromFileWithScanner();
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


    public static void saveCustomersToFile()throws IOException {
        //if I do not use throws, I must surround with try catch finally block, and in finally to close the buffer.

        Path customerPath = FileSystems.getDefault().getPath("customers_nio.txt");

        try (BufferedWriter customersFile = Files.newBufferedWriter(customerPath)) {
            for (Customer customer : customers) {
                customersFile.write(customer.getFirstName() + ", " + customer.getLastName() + ", " + customer.getId() + "\n");

            }
        }
    }


    public static void loadCustomersFromFile()throws IOException{
        Path customerPath = FileSystems.getDefault().getPath("customers_nio.txt");

        try(BufferedReader customersFile = Files.newBufferedReader(customerPath)){
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

    // alternative way to show how to use Scanner class
    public static void loadCustomersFromFileWithScanner()throws IOException{
        Path customerPath = FileSystems.getDefault().getPath("customers_nio.txt");

        try(Scanner scanner = new Scanner(Files.newBufferedReader(customerPath))){
            scanner.useDelimiter(", ");
            while (scanner.hasNextLine()){
                String firstName = scanner.next();
                scanner.skip(scanner.delimiter());
                String lastName = scanner.next();
                scanner.skip(scanner.delimiter());
                int id = Integer.parseInt(scanner.nextLine());


                Customer customer = new Customer(firstName, lastName, id);

                customers.add(customer);
            }
        }
    }



    public static void printCustomers(){
        for(Customer customer : customers){
            System.out.println(customer);
        }
    }
}
