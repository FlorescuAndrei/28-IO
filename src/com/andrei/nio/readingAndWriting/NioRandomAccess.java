package com.andrei.nio.readingAndWriting;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class NioRandomAccess {

    public static void main(String[] args) {


        try(FileOutputStream binFile = new FileOutputStream("data2.dat");
            FileChannel binChannel = binFile.getChannel()){

            //Put all data in the same buffer - an alternative way of putting Strings and ints in separate buffers like in NioBinaryData class example
            ByteBuffer buffer = ByteBuffer.allocate(100);

            byte[] outputBytes = "Hello World!".getBytes();
            buffer.put(outputBytes);
            //save the position on the file for random access
            long int1Pos = outputBytes.length;

            buffer.putInt(245);
            long int2Pos = int1Pos + Integer.BYTES;

            buffer.putInt(-98765);
            byte[] outputBytes2 = "Nice to meet you".getBytes();
            buffer.put(outputBytes2);
            long int3Pos = int2Pos + Integer.BYTES + outputBytes2.length;

            buffer.putInt(1000);
            buffer.flip();

            binChannel.write(buffer);

 //Read the file
            RandomAccessFile ra = new RandomAccessFile("data2.dat", "rwd");
            FileChannel channel = ra.getChannel();

       //Read sequential
            System.out.println("Sequential read");
            ByteBuffer readBuffer = ByteBuffer.allocate(100);
            channel.read(readBuffer);
            //switch from writing to the buffer to reading from the buffer
            readBuffer.flip();

            byte[] inputString = new byte[outputBytes.length];
            readBuffer.get(inputString);
            System.out.println("inputString = " + new String(inputString));

            System.out.println("int1 = " + readBuffer.getInt());
            System.out.println("int2 = " + readBuffer.getInt());


            byte[] inputString2 = new byte[outputBytes2.length];
            readBuffer.get(inputString2);
            System.out.println("inputString2 = " + new String(inputString2));

            System.out.println("int3 = " + readBuffer.getInt());

       //Read data in random way
            System.out.println("\nRandom read");

            ByteBuffer readBuffer2 = ByteBuffer.allocate(Integer.BYTES);
            //set position on file
            channel.position(int3Pos);
            channel.read(readBuffer2);
            //set position on buffer;
            readBuffer2.flip();
            System.out.println("int3 = " + readBuffer2.getInt());

            readBuffer2.flip();
            channel.position(int2Pos);
            channel.read(readBuffer2);
            readBuffer2.flip();
            System.out.println("int2 = " + readBuffer2.getInt());

            readBuffer2.flip();
            channel.position(int1Pos);
            channel.read(readBuffer2);
            readBuffer2.flip();
            System.out.println("int1 = " + readBuffer2.getInt());

            channel.close();
            ra.close();


        }catch (IOException e){
            e.printStackTrace();
        }
    }




}
