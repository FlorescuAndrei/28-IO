package com.andrei.nio.fileSystem.WalkFileTree;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class WalkFileTree {
    public static void main(String[] args) {

        try{
    //Directory content
    //return only the first level of contents.
            System.out.println("\n******Directory content******");
            Path directory = FileSystems.getDefault().getPath("Examples\\Dir2");

            DirectoryStream<Path> contents = Files.newDirectoryStream(directory);
            for(Path file: contents){
                System.out.println(file.getFileName());
            }

            //filter directory content

            //Glob ("*.dat") is a filter to show specific type of file, similar to a regular expression.
            DirectoryStream<Path> filterContents = Files.newDirectoryStream(directory, "*.dat");
            for(Path file: filterContents) {
                System.out.println("\nFiltered .dat result: " + file.getFileName());
            }

            //create a filter to show only the files
            DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
                @Override
                public boolean accept(Path entry) throws IOException {
                    return (Files.isRegularFile(entry));
                }
            };
            DirectoryStream<Path> filterContents2 = Files.newDirectoryStream(directory, filter);
            for(Path file: filterContents2) {
                System.out.println("Only files result: " + file.getFileName());
            }


        }catch (IOException e){
            e.printStackTrace();
        }

    //Walk Entire Tree. Entire Tree Content
        try{

            System.out.println("\n ----Walking Tree for Dir2 ---");
            Path dir2Path = FileSystems.getDefault().getPath("Examples" + File.separator + "Dir2");
            Files.walkFileTree(dir2Path, new PrintNames());

        }catch (IOException | DirectoryIteratorException e){
            e.printStackTrace();
        }
    }
}
