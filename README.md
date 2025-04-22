<h1 align="center">
  <p align="center">
    <img src="https://github.com/user-attachments/assets/89c0e841-a321-43b4-b8fa-cdd9cd2cd7c4" >
  </p>
  <p align="center"> PEOPLE 
  </p>
</h1>
<div>
  <p>
    This desktop application is an academic example of the use of the Model-View-Controller (MVC) code organization system and uses Java as an object-oriented programming language. The application allows the user to do CRUD operations of the defined entity (Person :raising_hand:) and works with different storage systems, both temporary and persistent.
  </p>
</div>
<div>
  <p align="center">
    <img src="https://img.shields.io/badge/License-GPL v3.0-blue">
    <img src="https://img.shields.io/badge/JDK->=17-red">
    <img src="https://img.shields.io/badge/Maven-4.0.0-green">
    <img src="https://img.shields.io/badge/OS-Windows, Linux-yellow">
    <img src="https://img.shields.io/badge/ObjectDB-2.9.0-orange">
    <img src="https://img.shields.io/badge/MySQL_Connector-8.0.25-purple">
    <img src="https://img.shields.io/badge/JDatePicker-2.0.3-white">
    <img src="https://img.shields.io/badge/Apache Commons_IO-2.5-brown">
    <img src="https://img.shields.io/badge/release-Latest version-black">
  </p>
</div>
<h2>🛠️How it works</h2>
<div>
  <p>The application code is distributed in the following packages: </p>
    <p><b>📦 Model.Class</b></p>
      <p><b>🗒️ Person.java</b></p> 
        <p><span>Class that defines the type of object with which the application works. In our case it structures the person :raising_hand: entity. The key attribute that  
                  distinguishes one person from another is their NIF. People with the same NIF cannot exist in the applicationThe three main MVC packages do not exchange    
                  basic data but rather exchange objects of this class.</span></p>
      <p><b>🗒️ PersonException.java</b></p>
        <p><span>Own exception thrown from the Controller package when an attempt is made to insert or delete a person who has not been previously registered. The management                   of the exception is also carried out within the Controller package by displaying an alert message to the user. It is not necessary to include it for the   
                  code to function, but since it is an academic activity it has been deemed appropriate to include it.</span></p>
    <p><b>📦 Controller</b></p>
      <p><b>🗒️ IController.java</b></p> 
        <p><span>Interface that defines the operations that the controller, and therefore the application, can perform regardless of the type of storage selected. These   
                  operations are: read one or all people, register a person, update a person, and delete one or all people. The use of an interface isolates the Controller  
                  package code from the rest of the application packages, minimizing the impact on future software updates.</span></p>
      <p><b>🗒️ ControllerImplementation.java</b></p> 
        <p><span>Class that implements the IController.java interface. It is responsible for scheduling and managing the events of the visual part. Thanks to these events, 
                  it is able to prepare the necessary structure for data storage, operate with these structures and show the information to the user if necessary. This block                   also manages exceptions that are mainly related to information access problems.</span></p>
    <p><b>📦 Model.DataAccessObject</b></p>
      <p><b>🗒️ IDAO.java</b></p> 
        <p><span>This interface defines the operations that will have to be performed on the data storage system chosen by the user. Depending on the system, the     
                  implementation varies. These operations are: read one or all people, register a person, update a person, and delete one or all people.The use of an 
                  interface allows different classes to be implemented with the appropriate code for data access according to the selected storage structure with minimal 
                  impact on the rest of the blocks. This way, the code is better organized and easier to read.</span></p>
      <p><b>🗒️ DAOArrayList.java</b></p> 
        <p><span>This class implements the IDAO interface and completes the code blocks of the functions so that they can operate with an ArrayList structure where objects                     are referenced.</span></p>
      <p><b>🗒️ DAOFile.java</b></p> 
        <p><span>This class implements the IDAO interface and completes the code of the functions so that they can work with files. Each object is decomposed into basic data                   and saved in the "dataFile.txt" file and the associated photo, if any, is saved with the name NIF.png in the "Photos" folder. Both the "dataFile.txt" file                    and the "Photos" folder are located under the "File" folder within the application's working folder.</span></p>
      <p><b>🗒️ DAOFileSerializable.java</b></p> 
        <p><span>This class implements the IDAO interface and completes the code of the functions so that they can work with files. Each object is saved in the       
                  "dataFile.ser" file. The "dataFile.ser" file is located under the "FileSer" folder within the application's working folder.</span></p>
      <p><b>🗒️ DAOHashMap.java</b></p> 
        <p><span>This class implements the IDAO interface and completes the code blocks of the functions so that they can operate with an HashMap structure where objects                     are referenced.</span></p>
      <p><b>🗒️ DAOJPA.java</b></p> 
        <p><span>This class implements the IDAO interface and completes the code blocks of the functions so that they can operate with a database designed for object storage                   using the Java Persistence API.</span></p>
      <p><b>🗒️ DAOSQL.java</b></p> 
        <p><span>This class implements the IDAO interface and completes the code blocks of the functions so that they can operate with a relational SQL database using the                     Java Database Connector (JDBC) API. In this type of storage, objects are broken down into basic elements and stored in tables, except for photos of people,                   which, if present, are stored in the "Photo" folder under the "SQL_Database" directory in the application's working folder. Only the paths to the images are                 stored in the database.</span></p>
    <p><b>📦 View</b></p>
      <p><span>The files in this package contain the graphical part of the application. They have been created with the Swing API. One of the functions of this block is to validate that the data entered by the user is correct. For example: that the NIF cannot contain letters in the numerical part or "strange" symbols, that the user name cannot contain numbers and that the images are in . png format and cannot exceed a size of 64KB.</span></p>
        <p><b>🗒️ DataStorageSelection.java</b></p> 
          <p><span>Class that allows the user to select the data storage system that the application will use. You can choose between volatile storage systems that                             reference "Person" objects such as ArrayList and HashMap and non-volatile ones. Within this last group you can choose between systems that store object                      information in a fractional way in simple data such as files (File) and relational databases (SQL) or systems that store objects as files combined with                      serialization (File - Serialization) and object-oriented databases (JPA).</span></p>
        <p><b>🗒️ Menu.java</b></p> 
          <p><span>Class that defines the main panel where the buttons with all the options implemented in the application are displayed and that the user can execute. If                       this panel is closed, the application also closes.</span></p>
        <p><b>🗒️ DropPhotoListener.java</b></p> 
          <p><span>Class that implements the DropTargetListener interface and allows the user to drag&drop images.</span></p>
        <p><b>🗒️ Delete.java</b></p> 
          <p><span>Class that allows deleting a person from their NIF.</span></p>
        <p><b>🗒️ Insert.java</b></p> 
          <p><span>Class that allows a person to be registered, with the NIF and name being mandatory elements.</span></p>
        <p><b>🗒️ Read.java</b></p> 
          <p><span>Class that allows you to search for a person based on their NIF.</span></p>
        <p><b>🗒️ ReadAll.java</b></p> 
          <p><span>Class that shows all registered people.</span></p>
        <p><b>🗒️ Update.java</b></p> 
          <p><span>Class that allows you to update all of a person's data, except their NIF.</span></p>
    <p><b>📦 Start</b></p>
      <p><b>🗒️ Start.java</b></p> 
        <p><span>Class that contains the application's "main" method.</span></p>
      <p><b>🗒️ Routes.java</b></p> 
        <p><span>Instead of using a text file as a configuration to host the location information for files and folders and the connection to remote storage systems, and                       since this is an academic application, it was decided to use a special class in Java called Enumeration.</span></p>
    <p><b>📦 OtherFuncitions</b></p>
      <p><b>🗒️ DataValidation.java</b></p> 
        <p><span>Class that contains a method for validating data entered by the user.</span></p>
<p align="center"><img width="578" alt="image" src="https://github.com/user-attachments/assets/e43413c1-9066-4301-8c1e-4be8e450f84d"></p>
</div>
<h1></h1>
 <p>The application flow control is detailed below:</p>
    <p>1️⃣ The application starts by executing the Start.java file that contains the "main" method. This method generates a ControllerImplementation (CIO) type object that             receives a DataStorageSelection (DSSO) type object as an argument. From CIO, the "listener" is programmed, which in this case will manage the event generated                from DSS0 when the user has selected which storage system to work with.</p>
      <p align="center"><img width="359" alt="image" src="https://github.com/user-attachments/assets/cf714817-eb7e-4831-87cd-50d6ceee14c9"></p>
    <p>2️⃣ Once the user has selected the storage system, the CIO prepares, with the help of the Routes.java file, and certifies that the system is available and generates             a DAO object to be able to access the methods that allow CRUD operations to be performed. If the system is available, the CIO shows the user the main screen of              the application (Menu.java); otherwise, the user is informed and the application is closed. If during the execution of the application the storage system becomes            unavailable, the application will also close after informing the user.</p>
      <p align="center"><img width="205" alt="image" src="https://github.com/user-attachments/assets/3bc08d87-2d8a-40fb-91b8-8720259ab3d6">
        <img width="234" alt="image" src="https://github.com/user-attachments/assets/92deabb9-228a-4a23-adc1-2f2a182bee47"></p>
      <p>The CIO is also responsible for programming a listener for each menu option. These events triggered from the visual side will cause the CIO to open a new screen               with a suitable format so that the user can enter the data and carry out the relevant option. Once the user has selected an option from the menu, the CIO opens a            new window and programs the "listeners" that allow him to perform the relevant operations against the storage system thanks to the DAO object.</p>
    <p>3️⃣ INSERT: in order to register a person, it is mandatory to enter the NIF and the name. If you try to register a person with an existing NIF, the application does             not allow it.</p>
     <p align="center"><img width="587" alt="image" src="https://github.com/user-attachments/assets/839dddbe-7ef8-41a0-b1e7-24500c60c404">
       <img width="265" alt="image" src="https://github.com/user-attachments/assets/103df1d6-c1b1-4a78-ad14-3787c0934ed3"></p>
    <p>4️⃣ READ: to search for a person you need to enter their NIF and the application will display the data they have registered. If there is no person registered                 with the NIF entered, the user will be informed.</p>
      <p align="center"><img width="589" alt="image" src="https://github.com/user-attachments/assets/162fac68-e791-468d-bfc3-5bb10015de7a">
        <img width="191" alt="image" src="https://github.com/user-attachments/assets/a7497a64-5e41-476c-83ed-44e1f50b74cc">
      </p>
    <p>5️⃣ UPDATE: any attribute of a person can be updated except their NIF. To update a person you need to enter their NIF. If there is no person registered with the NIF         entered, the user will be informed.</p>
      <p align="center"><img width="698" alt="image" src="https://github.com/user-attachments/assets/5b6ced22-15ed-46a8-91e5-fe5596db6577">
        <img width="191" alt="image" src="https://github.com/user-attachments/assets/f5cfa0bc-126b-44c0-92aa-27f483e53872">
      </p>
    <p>6️⃣ DELETE: to delete a person you need to enter their NIF. If there is no person registered with the NIF entered, the user will be informed.</p>
      <p align="center"><img width="381" alt="image" src="https://github.com/user-attachments/assets/d4b146f7-10bb-4801-a4d0-d07768c68c05">
        <img width="272" alt="image" src="https://github.com/user-attachments/assets/d39dbe00-c242-4c00-b006-756cae9f924f">
      </p>
    <p>7️⃣ READ ALL: the program displays a grid with the available data of all registered persons. If there is no registered person, the program informs the user.</p>
      <p align="center"><img width="642" alt="image" src="https://github.com/user-attachments/assets/ee6ce717-3286-4fb0-a34e-de429564a161">
        <img width="200" alt="image" src="https://github.com/user-attachments/assets/b906383e-0c3d-40fb-9308-6684b7d2c9e8">
      </p>
    <p>8️⃣ DELETE ALL: his option allows you to delete all registered user information. Before executing it, the program asks the user for confirmation.</p>
      <p align="center"><img width="239" alt="image" src="https://github.com/user-attachments/assets/291e9016-5e45-41e5-b998-2625f18f3891">
      </p>

<h2>🚀Getting started</h2>
<p>1️⃣ Download version 1.1.0 (.zip) of the project and import it with an IDE that allows you to manage projects developed with Maven, such as Netbeans.</p>
<p>2️⃣ Run the application from the IDE, you can use any storage system, but you need to read this about the options with databases, both SQL and JPA, and files: </p>
<p>2️⃣.1️⃣ SQL: I have used locally the <a href="https://sourceforge.net/projects/xampp/files/XAMPP%20Windows/8.2.12/xampp-windows-x64-8.2.12-0-VS16-installer.exe/download">MariaDB</a> database integrated into XAMPP with the root user and without a password. You can modify these parameters in the Routes.java file within the Start package.</p>
<p>2️⃣.2️⃣ JPA: I have used locally the <a href="https://www.objectdb.com/download/2.9.0">ObjectDB</a> database with the root admin and password a admin. You can modify these parameters in the Routes.java file within the Start package.</p>
<p>3️⃣ For the files I have also used local directories, but you can also use network drives. You can modify these parameters in the Routes.java file within the Start package.</p>
<p>4️⃣ If you want an executable .jar file you must create it yourself.</p>

