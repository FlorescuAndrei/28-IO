# 28-IO
Java IO, Java NIO. Reading and Writing. File System  

**Project Description**

#### IO
- Sequential Files
    -  character data  
    -  binary data primitive type  
    -  binary data serialization – writing and reading objects. 
-  Random Access Files  
    -  random write and read 

#### NIO with IO  
Use Java NIO Classes to create commonly used Java IO Streams
-  Sequential Files  
    -  character data
    -  serialization

#### NIO  
-   
    -  character data
    -  binary data

#### FileSystem
-	Paths
-	Copy, Move, Rename, Delete, Create
-	File Attributes
-	Read Existing Directory Content
-	Separator and Temp Files
-	Walk File Tree

**IO vs NIO**

Dillinger is currently extended with the following plugins.
Instructions on how to use them in your own application are linked below.

| IO | NIO |
| ------ | ------ |
| Work with Streams – process one character or byte at a time, sometimes buffered. | Work with data in Blocks – process one block at a time using channels and buffers. |
| File class | Path class replaces the File class|
| Buffer Reader/Writer is created with constructor (new). Works with File instances |Buffer Reader/Writer is created using java NIO classes. Works with Path instances.|
| Two instance to Read or Write(FileReader, FileWriter) | Only one instance to Read or Write the same data source |
| Blocking I/O – a thread will block when read or write |Non-Blocking I/O |

Java NIO is recomanded for:
-  large applications that use multiple threads
-  when working with file system (create, delete, move files or folders)  

Tips  
For FileSistems use NIO, for reading and writing file contents use IO.
