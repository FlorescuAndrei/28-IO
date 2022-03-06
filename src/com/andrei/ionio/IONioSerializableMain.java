package com.andrei.ionio;

import com.andrei.SerializableCustomer;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

//Write entire Objects
public class IONioSerializableMain {

    public static List<SerializableCustomer> customers = new LinkedList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {

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


    public static void saveCustomersToFile()throws IOException {
        Path customerPath = FileSystems.getDefault().getPath("customers1_nio.dat");
        try (ObjectOutputStream customersFile = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(customerPath)))){
            for (SerializableCustomer customer : customers) {
                customersFile.writeObject(customer);
                System.out.println("Customer write to file");

            }
        }
    }

    //load customer from file
    public static void loadCustomersFromFile()throws IOException, ClassNotFoundException{
        Path customerPath = FileSystems.getDefault().getPath("customers1_nio.dat");
        try(ObjectInputStream customersFile = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(customerPath)))){
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
