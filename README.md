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

<h3> In JDBC (Java Database Connectivity), there are several ways to insert data into a database, as well as different types of statements you can use. Here's a breakdown: </h3>

Ways to Insert Data in JDBC
<h5>Using Statement:</h5>

A Statement is used to execute a simple SQL query.
Example:
java
Copy code
Statement stmt = connection.createStatement();
String sql = "INSERT INTO employees (id, name, age) VALUES (1, 'John Doe', 30)";
int rowsInserted = stmt.executeUpdate(sql);
Using PreparedStatement:

<h5>PreparedStatement</h5> executes parameterized queries. It is precompiled and can be reused with different parameters.
Example:
java
Copy code
String sql = "INSERT INTO employees (id, name, age) VALUES (?, ?, ?)";
PreparedStatement pstmt = connection.prepareStatement(sql);
pstmt.setInt(1, 1);
pstmt.setString(2, "John Doe");
pstmt.setInt(3, 30);
int rowsInserted = pstmt.executeUpdate();
Using CallableStatement:

<h5>CallableStatement</h5> executes stored procedures in the database.
Example:
java
Copy code
CallableStatement cstmt = connection.prepareCall("{call insertEmployee(?, ?, ?)}");
cstmt.setInt(1, 1);
cstmt.setString(2, "John Doe");
cstmt.setInt(3, 30);
cstmt.execute();
Batch Insertions using Statement or PreparedStatement:

<h5>Batch processing</h5> allows executing multiple insert statements in a single database call, improving performance.
Example with PreparedStatement:
java
Copy code
String sql = "INSERT INTO employees (id, name, age) VALUES (?, ?, ?)";
PreparedStatement pstmt = connection.prepareStatement(sql);
for (Employee emp : employeeList) {
    pstmt.setInt(1, emp.getId());
    pstmt.setString(2, emp.getName());
    pstmt.setInt(3, emp.getAge());
    pstmt.addBatch();
}
int[] result = pstmt.executeBatch();
Different Types of Statements in JDBC
Statement:

Used for executing static SQL queries without parameters.
Suitable for simple queries like DDL (Data Definition Language) statements.
PreparedStatement:

Used for executing parameterized SQL queries.
Provides better performance and security (prevents SQL injection).
Supports dynamic queries with parameters.
CallableStatement:

Used for executing stored procedures.
Can handle IN, OUT, and INOUT parameters.
Suitable for complex operations encapsulated in stored procedures.
Batch Statements:

Allows grouping multiple SQL commands into a batch and executing them at once.
This can be done using Statement or PreparedStatement.
Improves performance when executing multiple insert, update, or delete operations.
Summary
Statement: Use for simple, static SQL queries.
PreparedStatement: Use for queries with parameters (dynamic queries).
CallableStatement: Use for executing stored procedures.
Batch Processing: Use for efficient insertion of multiple records.





























<h3>Resources:</h3>
https://www.javatpoint.com/java-jfilechooser
<br>
https://www.javatpoint.com/java-joptionpane

