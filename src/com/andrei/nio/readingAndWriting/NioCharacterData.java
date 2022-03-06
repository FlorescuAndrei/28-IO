package com.andrei.nio.readingAndWriting;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class NioCharacterData {

    public static void main(String[] args) {
// I created file data.txt and added first three line manually.

        try{
            Path dataPath = FileSystems.getDefault().getPath("data.txt");

            //the third parameter StandardOpenOption.APPEND if not passed the existing data will be deleted.
            Files.write(dataPath, "\nLine 4".getBytes("UTF-8"), StandardOpenOption.APPEND);

            // this load all file in memory. If it is a large file and do not want to load all in memory better to use Java IO
            List<String> lines = Files.readAllLines(dataPath);
            for(String line : lines){
                System.out.println(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }




}
