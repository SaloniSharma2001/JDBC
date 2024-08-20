# JDBC
This repo will contain the basic implementation of the JDBC connection with DB and backend code.

JDBC stands for Java Database Connectivity. It's an API provided by Oracle for Java applications to interact with different sets of databases.
Each DB type has its own DB drivers that we need to download, these drivers do the syntax conversion task. JDBC API only has interface-level implementation and hence, they are DB independent whereas drivers are DB dedicated.
We need to add these drivers to the classpath and then load them into the P.memory while our program is getting executed.
We have a connection (session) class in JAVA for JDBC whose parent class is in the Object class. As soon as our driver gets loaded into the p.mem, we need to establish a connection. 
This connection required authentication of the user (id, pwd) and the URL of the DB server.
Different microservices can use different DB since we do the configurations like:
DB driver name
Connection Time Out
Idle Time Out
Max Pool Size 
Pool Name
at the microservice level.



