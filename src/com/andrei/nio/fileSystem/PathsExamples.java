package com.andrei.nio.fileSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsExamples {

    public static void main(String[] args) {

   //Paths
        Path path = FileSystems.getDefault().getPath("WorkingDirectoryFile.txt");
        printFile(path);

//        Path filePath = FileSystems.getDefault().getPath("files","SubdirectoryFile.txt");

        Path filePath = Paths.get(".","files","SubdirectoryFile.txt");
        printFile(filePath);

        //"." - current directory
        //“..” - parent directory – move back up one level


        Path path2 = FileSystems.getDefault().getPath(".","files", "..", "files", "SubdirectoryFile.txt");
        System.out.println(path2.normalize().toAbsolutePath());

        //File somewhere on the computer
//        filePath = Paths.get("C:\\Users\\Andrei\\IdeaProjects\\StepFour\\OutThere.txt");
//        printFile(filePath);

        filePath = Paths.get(".");
        System.out.println(filePath.toAbsolutePath());

        System.out.println("******************************");

        Path path3 = FileSystems.getDefault().getPath("thisfeledoesntexist.txt");
        System.out.println(path3.toAbsolutePath());

 //Check if path exist
        filePath = FileSystems.getDefault().getPath("files");
        System.out.println("Exists = " + Files.exists(filePath));
        System.out.println("Exists = " + Files.exists(path3));


    }

    private static void printFile(Path path){
        try(BufferedReader fileReader = Files.newBufferedReader(path)){
            String line;
            while((line = fileReader.readLine()) != null){
                System.out.println(line);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
