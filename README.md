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


## Theory notes

### IO
IO can be performed using streams(character and binary) – process one character or byte at a time  
-  byte stream (binary data)	-  for obj., program variables or classes, etc.  
-  character data 		–  for spreadsheet, XML, JSON, etc.

Types of files:  
-  Serial(sequential) files 	– stream of data in order   
-  Random access files	- apply to files that allow jumping within the file retrieving or overwriting any data. Need some sort of index showing where a particular record is within the file.  

**Character data**  
File reader  - read one character at a time.  
Buffer Reader reads more characters and buffers the character into a character array. Will use in general a File Reader because reduce access time.  
File reader vs Buffer reader: File Reader reads directly from the disk while the Buffer loads a larger part of the file and then reads it (more efficient);


File Writer  
Buffer Writer – File Writer puts data into the Buffer and the Buffer writes data when is full. Prevents writing a small amount of data into the disk.

To get data from the file you can use Scanner with File Reader, or Buffer Reader.

**Byte Stream(Binary Data)**  
File Input Stream and File Output Stream instead of File Reader and File Writer.  
Buffer Input Stream and Buffer Output Stream.  
Data Input Stream and Data Output Stream: java classes that deals with bytes.
	
**Serialization**  
Serialization = process to transform an object into a form that can be stored and recreated.  
Object Input Stream and Object Output Stream to read and write objects.  
We have to make the class serializable  - implement the Serializable Interface.  
When you want an object to be serializable all its fields should be serializable.  
It is recommended to declare a private long field called serial version UID.
	
**Random Access File**  
File Pointer is an offset in the file where the next read or write starts from.  
Offset is the byte location in the file.  
Record = each set of related data (first name, last name, id make up the record for customer).  
Index holds the offset and length of the record.

With Random Access File we can not read-write objects.  
We do not use Buffer to access randomly because buffer is like a queue is sequential.  


### NIO  

NIO  the java.nio classes fall into two buckets:   
-  those that deal with the file system  
-  those that deal with reading and writing

Java NIO is recomanded for:  
- large applications that use multiple threads  
- when working with file system (create, delete, move files or folders)  


**Reading and Writing**  

-  Channel: data source you reading from or writing to (ex: a file).   
-  Buffer: container for the block of data that you want to read or write.  
-  Selectors allow a single thread to manage the I/O for multiple channels. In large enterprise app, not use in current project.

Buffer 
-  capacity = number of elements it can contain;  
-  position = index of the next element that can be read or written  
-  mark – is used by the reset() method – reset the position to the mark;  

Position : 
-  buffer position(position in buffer) 
-  file position(position in file)

Position in the file  = channel position.
Examples of setting channel position  
- channel.position(0) 
- channel.read(buffer, 0); -> set channel(file)position to 0;  

Position in the buffer 
-  flip() method reset the buffer position .  Always use flip() the buffer when passing from read to write and vice-versa
-  intBuffer.getInt(0) -> position 0 in the buffer  = ABSOLUT READ; -> the buffer position is not changed after exectuion
-  intBuffer.getInt() = RELATIVE READ -> buffer position is changed after execution and we must use flip() to go back;



Random Access way java NIO  
-  Random Access Files from Java IO  
-  Seekable byte channel interface.  Has the notion of the current position.(ex: channel.position(10)) 

**Threads**  
Sending data from one thread to another using  java NIO Pips.  
Pipes are used to transfer data between threads. One way connection so data can flow one way.  
Pipes have two channels  
-  Sync channel: one or more threads can write to the sync channel.  
-  Source channel: other thread or threads read from the source channel.

**File System**  

Copy one file to another  
- A)	Use methods from java.nio.channels.FileChannel class, if you have one of the files open (in the channel), using transferFrom() or transferTo() is more efficient.Good for **open files**.
- B)	Use method from java.nio.file  package. Most commonly used


Path  can be Absolute  or Relative. Attention You can create paths to files that do not exist, better to check if exist.  

Copy. Move. Rename. Delete. Create.  
File Attributes (Metadata): size, last modified time, time created, etc…  
Directory content.  
Glob: “*.txt” – Is a filter to show a specific type of file, similar to a regular expression.  
File.separator : Windows  \, Unix   /.  
Temp File  
Walk File Tree – view all files and subdirectories. Use  FileVisitor 

Symbolic Links example: desktop shortcut on windows.  
File Stores:  on windows: the drives: C:, D:

Path NIO <=> File IO

[BACK TO START PAGE](https://github.com/FlorescuAndrei/Start.git)


