package com.andrei.nio.readingAndWriting;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class NioBinaryData {

    public static void main(String[] args) {


        try(FileOutputStream binFile = new FileOutputStream("data.dat");
            FileChannel binChannel = binFile.getChannel()){

   //Write data
            System.out.println("Writing data");

            byte[] outputBytes ="Hello World!". getBytes();

            //write the byte array to the buffer. Modification to the array will change the buffer and modification to the buffer will change the array
            //wrap() method tells that we use the byte array as the buffer
            //wrap() method reset the buffer position to 0
            //wrap() takes care of creating and flipping the buffer
            ByteBuffer buffer = ByteBuffer.wrap(outputBytes);

            int numBytes = binChannel.write(buffer);
            System.out.println("numBytes written was: " + numBytes);

            //very important the size of the buffer in this case Integer.Bytes = 4
            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);

            //.put() method doesn't reset the buffer position
            intBuffer.putInt(245);

            //.flip()method reset the buffer position
            intBuffer.flip();

            numBytes = binChannel.write(intBuffer);
            System.out.println("numBytes written was: " + numBytes);

            //flip before .put() because buffer size is 4
            //flip change the position in the buffer not in the file.
            intBuffer.flip();
            intBuffer.putInt(-98756);
            //flip after.put() prior to the call to the .write()method
            // always flip when alternate reading and writhing. Hire we read from the buffer and write to the channel.
            intBuffer.flip();
            numBytes = binChannel.write(intBuffer);
            System.out.println("numBytes written was: " + numBytes);


    //Read data

        // Read the file using Java IO

            System.out.println("\nRead data using Java IO");

            //Random access file is part of the IO package
            RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");
            byte[] b = new byte[outputBytes.length];
            ra.read(b);
            System.out.println(new String(b));

            long int1 = ra.readInt();
            long int2 = ra.readInt();
            System.out.println(int1);
            System.out.println(int2);

        //Read the file using Java NIO

            System.out.println("\nRead data using Java NIO");
            System.out.println("Read String");

            FileChannel channel = ra.getChannel();
            //reset position in file because we use ra before
            channel.position(0);

            //test if it read from file or from memory. If read from file the result should be still "Hello World"
            outputBytes[0] = 'a';
            outputBytes[1] = 'b';

            //always flip when switching from writhing to reading
            buffer.flip();

            //without .flip()this will read the byte array from memory
            channel.read(buffer);

           //method 1
            System.out.println("Method 1");
            System.out.println("outputBytes = " + new String(outputBytes));


            //method 2
            System.out.println("Method 2");
            if(buffer.hasArray()){
                System.out.println("byte buffer = " + new String(buffer.array()));
            }

    //Read the integers

        //Relative Read
            System.out.println("\nRead integer - relative read");

            // the .read() call write the buffer and the .getInt() reads the buffer
            //flip change the position in the buffer not in the file.
            intBuffer.flip();
            int numBytesRead = channel.read(intBuffer);
            intBuffer.flip();
            System.out.println(intBuffer.getInt());
            intBuffer.flip();
            channel.read(intBuffer);
            intBuffer.flip();
            System.out.println(intBuffer.getInt());



        //Absolute Read
            System.out.println("\nRead integer - absolute read ");

            //reset position on file after the string
            channel.position(outputBytes.length);

            //reset position on buffer
           intBuffer.flip();

            channel.read(intBuffer);

            //begin reading the buffer from index 0
            System.out.println(intBuffer.getInt(0));

            intBuffer.flip();
            channel.read(intBuffer);
            System.out.println(intBuffer.getInt(0));






        }catch (IOException e){
            e.printStackTrace();
        }
    }




}
