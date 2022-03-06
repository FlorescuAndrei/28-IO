package com.andrei.io.randomAccessFiles;

import com.andrei.Customer;

import java.io.*;
import java.util.*;


public class RandomMain {

    // this class read selected customer directly from file

    public static List<Customer> customers = new LinkedList<>();
    private static Map<Integer, IndexRecord> index = new LinkedHashMap<>();
    private static RandomAccessFile ra;

    public static void main(String[] args) throws IOException {

        loadCustomersFromMemory();
        printCustomers();

        saveCustomersToFile();

        //must load index for getCustomer to work
        loadIndex();

        System.out.println(getCustomer(0));



    }



    public static List<Customer> loadCustomersFromMemory(){

        Customer jj = new Customer("Jane", "Jones", 0);
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

        try (RandomAccessFile rao = new RandomAccessFile("customers_rand.dat", "rwd")){
            // "rwd" indicates that we want to open the file for reading and writing, and we want rights to occur synchronously.


            rao.writeInt(customers.size());

            // each index contains 3 integers: customer id (not the field id_number), offset for the customer, size (length) of the customer record.
            // index size = numbers of customers * number of ints contained in each index (3 integers) * numbers of bytes contained in an Integer(Integer.Bytes)
            int indexSize = customers.size() * 3 * Integer.BYTES;

            // calculate the current position of the file pointer to the index size,
            // to account for the value that we've already written to the file + the number of bytes for this integer
            int customerStarts = (int) (indexSize + rao.getFilePointer() + Integer.BYTES);
            rao.writeInt(customerStarts);

            //we must write the customers then the index , because for index we need the location offset.
            //we build the index in memory as we write the locations

            long indexStart = rao.getFilePointer();

            //set the offset for the first customer
            // we need to use this value  to calculate a customer record length after we've written it to the file
            int startPointer = customerStarts;
            //move the File Pointer to the first customer offset.
            rao.seek(startPointer);

            for(Customer customer : customers){
                rao.writeInt(customer.getId());
                rao.writeUTF(customer.getFirstName());
                rao.writeUTF(customer.getLastName());

                //create the index for this customer
                IndexRecord record = new IndexRecord(startPointer, (int)(rao.getFilePointer() - startPointer));
                System.out.println("***" + record);
                // create the full list of indexes
                index.put(customer.getId(), record);

                //update the start pointer for the next location
                startPointer = (int) rao.getFilePointer();
            }

            //write index records to the file
            rao.seek(indexStart);
            for(Integer customerId : index.keySet()){
                rao.writeInt(customerId);
                rao.writeInt(index.get(customerId).getStartByte());
                rao.writeInt(index.get(customerId).getLength());
            }



        }
    }

    // load the index in memory.
    public static void loadIndex(){
        try{
            ra = new RandomAccessFile("customers_rand.dat", "rwd");

            int numCustomers = ra.readInt();
            long customerStartPoint = ra.readInt();

            //load the index in memory
            while(ra.getFilePointer() < customerStartPoint){
                int id = ra.readInt();
                System.out.println(id);
                int customerStart = ra.readInt();
                int customerLength = ra.readInt();

                IndexRecord record = new IndexRecord(customerStart, customerLength);
                System.out.println(record);
                index.put(id, record);
            }

        }catch (IOException e){
            System.out.println("IOE in load index method");
        }
    }

    public static Customer getCustomer(int customerId) throws  IOException{
        IndexRecord record = index.get(customerId);
        System.out.println("===" + record);
        ra.seek(record.getStartByte());
        int id = ra.readInt();
        System.out.println("Customer id =" + id);
        String firstName = ra.readUTF();
        String lastName = ra.readUTF();

        Customer customer = new Customer(firstName, lastName, id);
        return customer;
    }



    public static void printCustomers(){
        for(Customer customer : customers){
            System.out.println(customer);
        }
    }

    public static void close() throws IOException{
        ra.close();
    }
}
