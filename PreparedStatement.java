// We have prepared a statement that is used for dynamic and parameterized queries. When our query gets parameter at run time it is known as dynamic query.

import java.sql.*;

class PreparedStatement {
    try{
        Class.forName(databaseDriver);
        //DataBase URL structure jdbc:engineName://host:port/dbName
        String.url = "jdbc:mysql://localhost:3306/saloni";
        String username = "root";
        String password = "root";
        Connection con = DriverManger.getConnection(url, username, password);
        //Create a query
        String q = "insert into table1(tName,tCity) values (?,?)";
        //get the prepared statement object
        PreparedStatement pstmt = con.prepareStatement(q);
        //set the values to the query
        pstmt.setSting(1, "Saloni");
        pstmt.setSting(2, "Kolkata");
        //Since we are don't want any result set hence we shall use executeUpdate inseat of execute itself
        pstmt.executeUpdate();
        //We cannot give q as a parameter to the execute statement here as it will compile the q once again but in an incomplete way wihtout the value being set.
        System.out.println("Inserted");
        con.close();
    }catch(Exception e){
        e.printStackTrace();
    }
}