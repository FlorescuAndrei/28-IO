package com.andrei.io.sequentialFiles;

import com.andrei.Customer;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class IoBinaryData {

    public static List<Customer> customers = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        // if I do not use throw, I must surround with try catch block

//        loadCustomersFromMemory();
//        printCustomers();
//
//        saveCustomersToBinaryFile();

        loadCustomersFromBinaryFile();
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



    //save customers to binary file
    public static void saveCustomersToBinaryFile()throws IOException {
        //if I do not use throws, I must surround with try catch finally block, and in finally to close the buffer.

        try (DataOutputStream customersFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("customers.dat")))) {
            for (Customer customer : customers) {
                customersFile.writeUTF(customer.getFirstName());
                customersFile.writeUTF(customer.getLastName());
                customersFile.writeInt(customer.getId());

            }
        }
    }

    //load customer from binary file
    public static void loadCustomersFromBinaryFile()throws IOException {
        try (DataInputStream customersFile = new DataInputStream(new BufferedInputStream(new FileInputStream("customers.dat")))) {
            //handle end of file
            boolean eof = false;
            while (!eof) {
                try {
                    String firstName = customersFile.readUTF();
                    String lastName = customersFile.readUTF();
                    int id = customersFile.readInt();

                    customers.add(new Customer(firstName, lastName, id));
                } catch (EOFException e) {
                    eof = true;
                }

            }
        }

    }



    public static void printCustomers(){
        for(Customer customer : customers){
            System.out.println(customer);
        }
    }
}
