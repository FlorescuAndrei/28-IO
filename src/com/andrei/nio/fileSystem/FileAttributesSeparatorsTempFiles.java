package com.andrei.nio.fileSystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class FileAttributesSeparatorsTempFiles {

    public static void main(String[] args) {
        try {

            //File Attributes (Metadata)
            System.out.println("****File Attributes*****");
            Path filePath = FileSystems.getDefault().getPath("Examples", "Dir1", "file1.txt");
            long size = Files.size(filePath);
            System.out.println("size = " + size);
            System.out.println("Last modified " + Files.getLastModifiedTime(filePath));

            // get all attributes
            BasicFileAttributes attributes = Files.readAttributes(filePath, BasicFileAttributes.class);
            System.out.println("size = " + attributes.size());
            System.out.println("last modified = " + attributes.lastModifiedTime());
            System.out.println("created = " + attributes.creationTime());
            System.out.println("is directory = " +  attributes.isDirectory());
            System.out.println("is regular file = " + attributes.isRegularFile());


            //File Separator
            System.out.println("\n File Separator / \\ ");
            String separator = File.separator;
            System.out.println(separator);

            separator = FileSystems.getDefault().getSeparator();
            System.out.println(separator);

            Path anotherFilePath = FileSystems.getDefault().getPath("Examples" + File.separator + "Dir2" + File.separator + "file1.txt");
            System.out.println(Files.size(anotherFilePath));

            //Temp Files
            System.out.println("\nTemporary file");
            Path tempFile = Files.createTempFile("myapp", ".appext");
            System.out.println("Temporary file path = " + tempFile.toAbsolutePath());

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
