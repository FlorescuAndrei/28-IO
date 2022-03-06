package com.andrei.nio.fileSystem;

import java.io.IOException;
import java.nio.file.*;

public class CopyMoveRenameDeleteCreate {
    public static void main(String[] args) {

        try{

            //copy file
            Path sourceFile = FileSystems.getDefault().getPath("Examples", "file1.txt");
            Path copyFile = FileSystems.getDefault().getPath("Examples", "file1copy.txt");
            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);

            //copy directory  but not the files it contains. To copy all you have to walk the file tree.
            sourceFile = FileSystems.getDefault().getPath("Examples", "Dir1");
            copyFile = FileSystems.getDefault().getPath("Examples", "Dir4");
            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);

            // move file
            Path fileToMove = FileSystems.getDefault().getPath("Examples", "file1copy.txt");
            Path destination = FileSystems.getDefault().getPath("Examples", "Dir1", "file1copy.txt");
            Files.move(fileToMove, destination, StandardCopyOption.REPLACE_EXISTING);

            //rename file
//            Path fileToRename = FileSystems.getDefault().getPath("Examples", "file4.txt");
//            Path renamedFile = FileSystems.getDefault().getPath("Examples", "file4rename.txt");
//            Files.move(fileToRename, renamedFile);

            //delete file

            Path fileToDelete = FileSystems.getDefault().getPath("Examples", "Dir1", "file1copy.txt");
            Files.deleteIfExists(fileToDelete);

            //create file
            Path fileToCreate =FileSystems.getDefault().getPath("Examples", "file5.txt");
//            Files.createFile(fileToCreate);

            //create directory
            Path dirToCreate = FileSystems.getDefault().getPath("Examples", "Dir5");
//            Files.createDirectory(dirToCreate);

            Path dirToCreate2 = FileSystems.getDefault().getPath("Examples", "Dir2\\Dir3\\Dir4\\Dir5\\Dir6");
            Files.createDirectories(dirToCreate2);

            System.out.println("Done!");

        }catch (IOException | DirectoryIteratorException e){
            e.printStackTrace();
        }
    }
}
