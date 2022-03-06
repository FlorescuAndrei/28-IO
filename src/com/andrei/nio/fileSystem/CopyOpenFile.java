package com.andrei.nio.fileSystem;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class CopyOpenFile {

    public static void main(String[] args) {


        try(RandomAccessFile ra = new RandomAccessFile("data3.dat", "rwd");
            FileChannel channel = ra.getChannel()){

//Write the file
            ByteBuffer buffer = ByteBuffer.allocate(100);

            byte[] outputBytes = "Hello World!".getBytes();
            buffer.put(outputBytes);
            buffer.putInt(245);

            buffer.flip();
            channel.write(buffer);

 //Read the file
            channel.position(0);

            ByteBuffer readBuffer = ByteBuffer.allocate(100);
            channel.read(readBuffer);

            readBuffer.flip();

            byte[] inputString = new byte[outputBytes.length];
            readBuffer.get(inputString);
            System.out.println("inputString = " + new String(inputString));
            System.out.println("int1 = " + readBuffer.getInt());

//Copy open file using methods from FileChannel.class
            RandomAccessFile copyFile = new RandomAccessFile("data3copy.dat", "rw");
            FileChannel copyChannel = copyFile.getChannel();

            //.transferFrom() method use relative position of the channel from which copy data.
            // so we must set the position to 0.
            // there are methods that use absolut position of the channel.
            channel.position(0);
            long numTransferred = copyChannel.transferFrom(channel,0, channel.size());

            //transferTo() method example. Same result
//            long numTransferred = channel.transferTo(0, channel.size(), copyChannel);



            copyChannel.close();
            copyFile.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }




}
