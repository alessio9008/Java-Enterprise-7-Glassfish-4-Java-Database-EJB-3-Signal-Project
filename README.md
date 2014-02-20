Java-Enterprise-7-Glassfish-4-Java-Database-EJB-3-Signal-Project
================================================================

The system consist of: 
- A front-end web services (SOAP) that take the users' signals. 
- An application components (EJB 3.0). 

The application: 
-	Makes the parsing of the signal. 
- Manages the insert, modify and delete of reservations in a database (Java DB). 
- At regular intervals, logs in a file the performed activities. 
- Keeps the statistics on the received signal. 

For each district, the application maintains the average: 
- Number of the week's signals. - Number of the month's signals. 
- Number of the high priority month's signals. 
- Age of the users that send the signals. 

The signals are built with: 
- Id. 
- Name, surname and age of the user. 
- Id district. 
- Priority (HIGH – LOW - AUTO). 
 
The system has been designed with a BPMN workflow and was deployed in a remote virtual machine (Amazon - AWS). 
The client through which the user communicate with the system in particular with the web services is built with a web page and one servlet.

This project was developed by Alessio Oglialoro, Gregory Callea and Riccardo Nocita
