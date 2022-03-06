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
File reader  - read one character at a time
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
	We have to make the class serializable  - implement the Serializable Interface
When you want an object to be serializable all its fields should be serializable.
	It is recommended to declare a private long field called serial version UID.
	
**Random Access File**
	File Pointer is an offset in the file where the next read or write starts from.
	Offset is the byte location in the file.
Record = each set of related data (first name, last name, id make up the record for customer).
Index holds the offset and length of the record.

With Random Access File we can not read-write objects.
We do not use Buffer to access randomly because buffer is like a queue is sequential.


