package com.andrei.io.sequentialFiles;

import com.andrei.SerializableCustomer;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

//Write entire Objects
public class IoSerializable {

    public static List<SerializableCustomer> customers = new LinkedList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // if I do not use throw, I must surround with try catch block
//
//        loadCustomersFromMemory();
//        printCustomers();
//
//        saveCustomersToFile();

        loadCustomersFromFile();
        printCustomers();

        // if you get invalid stream header error  first save customer to file then run again loadCustomerFromFile
    }



    public static List<SerializableCustomer> loadCustomersFromMemory(){



        SerializableCustomer jj = new SerializableCustomer("Jane", "Jones", 000);
        SerializableCustomer jd = new SerializableCustomer("John", "Doe", 111);
        SerializableCustomer ms = new SerializableCustomer("Mary", "Smith", 222);
        SerializableCustomer mw = new SerializableCustomer("Mike", "Wilson", 333);

        customers.add(jj);
        customers.add(jd);
        customers.add(ms);
        customers.add(mw);

        return customers;
    }



    //save customers to file
    public static void saveCustomersToFile()throws IOException {

        try (ObjectOutputStream customersFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("customers2.dat")))){
            for (SerializableCustomer customer : customers) {
                customersFile.writeObject(customer);
                System.out.println("Customer write to file");

            }
        }
    }

    //load customer from file
    public static void loadCustomersFromFile()throws IOException, ClassNotFoundException{
        try(ObjectInputStream customersFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("customers2.dat")))){
            boolean eof = false;
            while(!eof){
                try{
                    SerializableCustomer customer = (SerializableCustomer) customersFile.readObject();
                    customers.add(customer);
                }catch (EOFException e){
                    eof = true;
                }

            }
        }
    }



    public static void printCustomers(){
        for(SerializableCustomer customer : customers){
            System.out.println(customer);
        }
    }
}
