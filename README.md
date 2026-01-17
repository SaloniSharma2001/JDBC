# JDBC

<h1>JDBC (Java Database Connectivity)</h1>

<p>This repository contains complete and well-structured notes on <b>JDBC (Java Database Connectivity)</b>, written specifically for understanding core JDBC concepts and for practical backend development.</p>

<hr/>

<h2>1. Introduction to JDBC</h2>

<h3>What is JDBC?</h3>
<p><b>JDBC (Java Database Connectivity)</b> is a standard Java API provided by Oracle that allows Java applications to interact with different sets of databases. It acts as a bridge between Java applications and databases.
Each DB type has its own DB drivers that we need to download; these drivers do the syntax conversion task. JDBC API only has interface-level implementation, and hence, they are DB independent, whereas drivers are DB dedicated.
We need to add these drivers to the classpath and then load them into the P.memory while our program is executing.
We have a connection (session) class in JAVA for JDBC whose parent class is in the Object class. As soon as our driver gets loaded into the p.mem, we need to establish a connection. 
This connection required authentication of the user (id, pwd) and the URL of the DB server.</p>

<h3>Why JDBC?</h3>
<ul>
  <li>Database-independent API</li>
  <li>Supports multiple databases</li>
  <li>Standard way to execute SQL from Java</li>
  <li>Used in enterprise and microservice-based applications</li>
</ul>

<h3>JDBC Architecture</h3>
<ul>
  <li><b>Java Application</b></li>
  <li><b>JDBC API</b> (Interfaces like Connection, Statement, ResultSet)</li>
  <li><b>JDBC Driver</b></li>
  <li><b>Database</b></li>
</ul>

<p>The JDBC API only provides <b>interfaces</b>. Actual implementations are provided by database-specific drivers.</p>

<h3>JDBC Drivers</h3>

<h4>Type 1: JDBC-ODBC Bridge Driver</h4>
<ul>
  <li>Uses ODBC driver</li>
  <li>Platform dependent</li>
  <li>Deprecated</li>
</ul>

<h4>Type 2: Native-API Driver</h4>
<ul>
  <li>Uses database native libraries</li>
  <li>Faster than Type 1</li>
  <li>Platform dependent</li>
</ul>

<h4>Type 3: Network Protocol Driver</h4>
<ul>
  <li>Uses middleware server</li>
  <li>Database independent</li>
</ul>

<h4>Type 4: Thin Driver (Most Common)</h4>
<ul>
  <li>Pure Java driver</li>
  <li>Directly communicates with DB</li>
  <li>Best performance</li>
  <li>Example: MySQL, PostgreSQL, Oracle drivers</li>
</ul>

<p>Different microservices can use different DBs since we do the configurations at the microservice level like:</p>

<ul>
    <li>DB driver name</li>
    <li>Connection Time Out</li>
    <li>Idle Time Out</li>
    <li>Max Pool Size</li>
    <li>Pool Name</li>
</ul>

<hr/>

<h2>2. Setting Up JDBC Environment</h2>

<ul>
  <li>Download database-specific JDBC driver</li>
  <li>Add driver to classpath or dependency manager (Maven/Gradle)</li>
  <li>Configure DB URL, username, and password</li>
</ul>

<hr/>

<h2>3. Establishing a Database Connection</h2>

<h3>Loading the Driver</h3>
<pre><code>Class.forName("com.mysql.cj.jdbc.Driver");</code></pre>

<h3>Creating a Connection</h3>
<pre><code>Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/testdb",
    "username",
    "password"
);</code></pre>

<h3>Database URL Format</h3>
<ul>
  <li>MySQL: jdbc:mysql://host:port/db</li>
  <li>PostgreSQL: jdbc:postgresql://host:port/db</li>
  <li>Oracle: jdbc:oracle:thin:@host:port:sid</li>
</ul>

<h3>Closing Connection</h3>
<pre><code>con.close();</code></pre>

<hr/>

<h2>4. JDBC Statements: <h3> In JDBC (Java Database Connectivity), there are several ways to insert data into a database, as well as different types of statements you can use.</h3></h2>

<h3>Statement</h3>
<p>Used for executing static queries without parameters.
Suitable for simple queries like DDL (Data Definition Language) statements.</p>
<pre><code>Statement stmt = con.createStatement();
stmt.executeUpdate("INSERT INTO emp VALUES (1, 'John')");</code></pre>

    Statement stmt = connection.createStatement();
    String sql = "INSERT INTO employees (id, name, age) VALUES (1, 'John Doe', 30)";
    int rowsInserted = stmt.executeUpdate(sql);

<h3>PreparedStatement</h3>
<p>Used for executing parameterized queries.
Provides better performance and security (prevents SQL injection).
Supports dynamic queries with parameters. It is precompiled and can be reused with different parameters.</p>
<pre><code>PreparedStatement ps = con.prepareStatement(
    "INSERT INTO emp VALUES (?, ?)"
);
ps.setInt(1, 1);
ps.setString(2, "John");
ps.executeUpdate();</code></pre>

    String sql = "INSERT INTO employees (id, name, age) VALUES (?, ?, ?)";
    PreparedStatement pstmt = connection.prepareStatement(sql);
    pstmt.setInt(1, 1);
    pstmt.setString(2, "John Doe");
    pstmt.setInt(3, 30);
    int rowsInserted = pstmt.executeUpdate();

<h3>CallableStatement -- IN, OUT, INOUT Parameters</h3>
<p>Used to execute stored procedures. Can handle IN, OUT, and INOUT parameters.
Suitable for complex operations encapsulated in stored procedures.</p>
<pre><code>CallableStatement cs = con.prepareCall("{call add_emp(?, ?)}");
cs.setInt(1, 1);
cs.setString(2, "John");
cs.execute();</code></pre>

    CallableStatement cstmt = connection.prepareCall("{call insertEmployee(?, ?, ?)}");
    cstmt.setInt(1, 1);
    cstmt.setString(2, "John Doe");
    cstmt.setInt(3, 30);
    cstmt.execute();

<h3>IN Parameters</h3>
<p>Used to pass input values to stored procedures. Value is sent from Java → Database. The database can read it. The database cannot modify it and send it back</p>
<h4>Stored Procedure (DB Side)</h4>

      CREATE PROCEDURE get_employee_by_id(
          IN emp_id INT
      )
      BEGIN
          SELECT * FROM employee WHERE id = emp_id;
      END;

<h4>Java Coden(Callable Statement)</h4>

    CallableStatement cs =
            con.prepareCall("{call get_employee_by_id(?)}");
    
    cs.setInt(1, 101);   // IN parameter
    ResultSet rs = cs.executeQuery();
    //Java sends 101 → DB uses it → Java does not receive any modified value


<h3>OUT Parameters</h3>
<p>Used to receive values from stored procedures. Java does NOT send a value. Database calculates a value. Database returns it back to Java</p>

<h4>Stored Procedure (DB Side)</h4>

    CREATE PROCEDURE get_employee_count(
        OUT total_count INT
    )
    BEGIN
        SELECT COUNT(*) INTO total_count FROM employee;
    END;

<h4>Java Coden(Callable Statement)</h4>

    CallableStatement cs =
            con.prepareCall("{call get_employee_count(?)}");
    
    cs.registerOutParameter(1, Types.INTEGER); // OUT parameter
    cs.execute();
    
    int count = cs.getInt(1);
    System.out.println("Total employees: " + count);
    //DB decides the value → Java receives it


<h3>INOUT Parameters</h3>
<p>Used to pass a value and receive a modified value. Java sends a value. The database modifies the same value. Modified value is returned to Java</p>

<h4>Stored Procedure (DB Side)</h4>

    CREATE PROCEDURE increment_salary(
        INOUT salary INT
    )
    BEGIN
        SET salary = salary + 5000;
    END;

<h4>Java Coden(Callable Statement)</h4>

    CallableStatement cs =
            con.prepareCall("{call increment_salary(?)}");
    
    cs.setInt(1, 40000);                       // IN
    cs.registerOutParameter(1, Types.INTEGER); // OUT
    
    cs.execute();
    
    int updatedSalary = cs.getInt(1);
    System.out.println("Updated Salary: " + updatedSalary);
    //Java sends 40000 → DB changes it → Java gets 45000

<h3>Why Stored Procedures?</h3>
<ul>
  <li>Business logic at DB level</li>
  <li>Better performance</li>
  <li>Security (controlled access)</li>
</ul>


<h3>Batch Processing: Allows grouping multiple SQL commands into a batch and executing them at once.
This can be done using Statement or PreparedStatement.
Improves performance when executing multiple insert, update, or delete operations.</h3>
<pre><code>ps.addBatch();
ps.executeBatch();</code></pre>

Batch Insertions using Statement or PreparedStatement:

Batch processing allows executing multiple insert statements in a single database call, improving performance.<br>
Example with PreparedStatement:

    String sql = "INSERT INTO employees (id, name, age) VALUES (?, ?, ?)";
    PreparedStatement pstmt = connection.prepareStatement(sql);
    for (Employee emp : employeeList) {
        pstmt.setInt(1, emp.getId());
        pstmt.setString(2, emp.getName());
        pstmt.setInt(3, emp.getAge());
        pstmt.addBatch();
    }
    int[] result = pstmt.executeBatch();

<hr/>

<h2>5. ResultSet and Data Retrieval</h2>

<h3>ResultSet</h3>
<p>Holds data returned from SELECT queries.</p>
<pre><code>ResultSet rs = stmt.executeQuery("SELECT * FROM emp");
while(rs.next()) {
    System.out.println(rs.getInt("id") + " " + rs.getString("name"));
}</code></pre>

<h3>ResultSet Navigation</h3>
<ul>
  <li>next()</li>
  <li>previous()</li>
  <li>absolute(int)</li>
</ul>

<h3>ResultSetMetaData</h3>
<h4>What is ResultSetMetaData?</h4>
<p>ResultSetMetaData provides information about the structure of data returned by a query.</p>

<h4>What Information Can It Provide?</h4>
<ul>
  <li>Number of columns</li>
  <li>Column names</li>
  <li>Column data types</li>
</ul>

<h4>Why is it Useful?</h4>
<ul>
  <li>Dynamic reports</li>
  <li>Generic frameworks</li>
  <li>Excel/CSV exports</li>
</ul>
<pre><code>ResultSetMetaData meta = rs.getMetaData();</code></pre>

<hr/>

<h2>6. Transaction Management</h2>

<h3>Auto-commit</h3>
<pre><code>con.setAutoCommit(false);</code></pre>

<h3>Commit and Rollback</h3>
<pre><code>con.commit();
con.rollback();</code></pre>

<h3>Savepoints</h3>
<pre><code>Savepoint sp = con.setSavepoint();</code></pre>

<hr/>

<h2>7. Exception Handling in JDBC</h2>

<h3>SQLException</h3>
<ul>
  <li>getMessage()</li>
  <li>getErrorCode()</li>
  <li>getSQLState()</li>
</ul>

<h3>SQLWarning</h3>
<p>Non-fatal warnings during execution.</p>

<hr/>

<h2>8. Advanced JDBC Concepts</h2>

<h3>Connection Pooling</h3>
<ul>
  <li>HikariCP</li>
  <li>Apache DBCP</li>
  <li>C3P0</li>
</ul>

<h3>DataSource</h3>
<p>Preferred alternative to DriverManager in enterprise apps.</p>

<h3>RowSet</h3>
<ul>
  <li>JdbcRowSet</li>
  <li>CachedRowSet</li>
  <li>WebRowSet</li>
  <li>FilteredRowSet</li>
  <li>JoinRowSet</li>
</ul>

<h3>LOBs</h3>
<ul>
  <li>BLOB (Binary Large Object)</li>
  <li>CLOB (Character Large Object)</li>
</ul>

<h3>Auto-generated Keys</h3>
<pre><code>Statement stmt = con.createStatement(
    Statement.RETURN_GENERATED_KEYS
);</code></pre>

<hr/>

<h2>9. JDBC in Web & Enterprise Applications</h2>

<ul>
  <li>Servlets & JSP</li>
  <li>Spring JDBC Template</li>
  <li>ORM tools (Hibernate, JPA)</li>
</ul>

<hr/>

<h2>10. Best Practices</h2>

<ul>
  <li>Always close JDBC resources</li>
  <li>Use PreparedStatement</li>
  <li>Use connection pooling</li>
  <li>Avoid hardcoded credentials</li>
  <li>Handle exceptions properly</li>
  <li>Log SQL queries</li>
</ul>

<hr/>

<h2>11. Practical Implementations</h2>

<ul>
  <li>CRUD operations using JDBC</li>
  <li>Console-based JDBC applications</li>
  <li>Web-based JDBC applications</li>
  <li>Integration with Spring MVC</li>
</ul>

<hr/>

<h2>Connection Pooling</h2>

<h3>What is Connection Pooling?</h3>
<p>Connection pooling is a technique where a set of database connections is created once and reused multiple times instead of creating a new connection for every request.</p>

<h3>Why Connection Pooling is Needed?</h3>
<ul>
  <li>Creating a DB connection is expensive (network + authentication)</li>
  <li>High-traffic applications may create thousands of connections</li>
  <li>Too many connections can crash the database</li>
</ul>

<h3>How Connection Pooling Works</h3>
<ul>
  <li>Application requests a connection</li>
  <li>Pool provides an existing idle connection</li>
  <li>After use, connection is returned to pool</li>
  <li>Connection is NOT closed physically</li>
</ul>

<h3>Common Pool Configurations</h3>
<ul>
  <li>Max pool size</li>
  <li>Idle timeout</li>
  <li>Connection timeout</li>
</ul>

<hr/>

<h2> SQLException and SQLWarning</h2>

<h3>SQLException</h3>
<p>Represents database access errors.</p>

<h3>Error Code</h3>
<p>Database-specific error number.</p>

<h3>SQLState</h3>
<p>Standardized 5-character code representing error category.</p>

<h3>SQLWarning</h3>
<p>Non-fatal warnings that do not stop execution.</p>

<hr/>

<h2> Connection Pooling with DataSource</h2>

<h3>What is DataSource?</h3>
<p>DataSource is an interface that provides connections using pooling and configuration.</p>

<h3>Why DataSource over DriverManager?</h3>
<ul>
  <li>Better performance</li>
  <li>External configuration</li>
  <li>Enterprise-ready</li>
</ul>

<hr/>

<h2>Performance Tuning in JDBC</h2>

<ul>
  <li>Use PreparedStatement</li>
  <li>Use connection pooling</li>
  <li>Use batch processing</li>
  <li>Fetch only required columns</li>
</ul>

<hr/>

<h2>Handling Connection Leaks</h2>

<h3>What is a Connection Leak?</h3>
<p>A connection leak occurs when a connection is not closed or returned to the pool.</p>

<h3>Impact</h3>
<ul>
  <li>Pool exhaustion</li>
  <li>Application slowdown</li>
  <li>Database outage</li>
</ul>

<h3>Prevention</h3>
<ul>
  <li>Use try-with-resources</li>
  <li>Use pool leak detection</li>
  <li>Always close resources</li>
</ul>

<hr/>

<h2>Conclusion</h2>
<p>JDBC is the foundation of Java-based database interaction. Understanding JDBC is essential before moving to advanced frameworks like Hibernate and Spring Data JPA.</p>
<ul>
    <li>Statement: Use for simple, static SQL queries.</li>
    <li>PreparedStatement: Use for queries with parameters (dynamic queries).</li>
    <li>CallableStatement: Use for executing stored procedures.</li>
    <li>Batch Processing: Use for efficient insertion of multiple records.</li>
</ul>
































<h3>Resources:</h3>
https://www.javatpoint.com/java-jfilechooser
<br>
https://www.javatpoint.com/java-joptionpane

